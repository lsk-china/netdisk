package com.lsk.netdisk.file.helpers;

import com.lsk.netdisk.common.redis.core.RedisComponent;
import com.lsk.netdisk.file.mappers.DirsMapper;
import com.lsk.netdisk.file.mappers.FilesMapper;
import com.lsk.netdisk.file.mappers.ServersMapper;
import com.lsk.netdisk.file.model.Dir;
import com.lsk.netdisk.file.model.File;
import com.lsk.netdisk.file.model.Server;
import com.lsk.netdisk.file.utils.FileSizeUtil;
import com.lsk.netdisk.file.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FileHelper {
	private static DirsMapper dirsMapper = SpringUtil.getBean(DirsMapper.class);
	private static FilesMapper filesMapper = SpringUtil.getBean(FilesMapper.class);
	private static ServersMapper serversMapper = SpringUtil.getBean(ServersMapper.class);
	private static RedisComponent redis = SpringUtil.getBean(RedisComponent.class);
	private static RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);
	public static Map<String,List<?>> ls(String path, Integer uid){
		List<File> files = filesMapper.ls(path,uid);
		List<Dir> dirs = dirsMapper.lsDirs(uid,path);
		Map<String,List<?>> result = new HashMap<>();
		result.put("files",files);
		result.put("dirs",dirs);
		return result;
	}
	public static void setWorkingDir(String dir,Integer uid){
		redis.set(uid+"-WORKDIR",dir,0);
	}
	public static void mkdir(String name,Integer uid){
		String parentDir = redis.get(uid+"-WORKDIR",String.class);
		Dir dir = new Dir();
		dir.setName(name);
		dir.setOwnerID(uid);
		dir.setParent(parentDir);
		dirsMapper.addDir(dir);
	}
	public static void mkUserDir(String username,Integer uid){
		Dir dir = new Dir();
		dir.setName(username);
		dir.setParent("/");
		dir.setOwnerID(uid);
		dirsMapper.addDir(dir);
	}
	public static void uploadFile(MultipartFile file) throws Exception{
		java.io.File dir = new java.io.File("D://tmp/netdisk");
		java.io.File tmpFile = new java.io.File(dir,file.getOriginalFilename());
		file.transferTo(tmpFile);
		Server server = chooseServer(file);
		String uploadURL = server.getUploadURL();

	}
	private static Server chooseServer(MultipartFile file){
		double fileSize = FileSizeUtil.FormatFileSize(file.getSize(),FileSizeUtil.SIZETYPE_GB);
		List<Server> servers = serversMapper.queryAllServers();
		for(Server server : servers){
			try {
				String extraSpaceURL = server.getExtraSpaceURL();
				String extraSpaceString = restTemplate.getForObject(extraSpaceURL, String.class);
				double serverExtraSpace = Double.parseDouble(extraSpaceString);
				if (fileSize > serverExtraSpace){
					continue;
				}else{
					return server;
				}
			}catch (Exception e){
				log.error("Error",e);
				throw new RuntimeException(e);
			}
		}
		throw new RuntimeException("No server has enough server!");
	}
}
