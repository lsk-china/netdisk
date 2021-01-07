<template>
  <div class="mainContainer">
    <img class="background" src="../assets/login/background.jpg"/>
    <div class="login">
      <h1 class="title">请登录</h1>
      <div class="inputs">
        <div>
          <span class="label">用户名:</span>
          <el-input class="input" v-model="username"></el-input><br>
        </div>
        <div>
          <span class="label">密码：</span>
          <el-input class="input" type="password" v-model="password"></el-input>
        </div>
        <el-button class="submit" type="primary" @click="login()">登录</el-button>
      </div>
      <router-link class="register-link" to="/register">注册</router-link>
    </div>
  </div>
</template>

<script>
import login from '@/api/login'
import qs from 'qs'

export default {
  name: 'login',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  created () {
    login.getToken()
  },
  methods: {
    login () {
      let username = this.username
      let password = this.password
      let data = qs.stringify({
        username: username,
        password: password
      })
      let resp = login.login(data)
      console.log(resp.data)
      while (this.$common.resp === null);
      console.log(this.$common.resp.data)
      if (this.$common.resp.data.data === 'Success.') {
        console.log('debug')
        this.$router.push("/index")
      }
    }
  }
}
</script>

<style scoped>
  .mainContainer{
    width: 100%;
    height: 100%;
    display: flex;
    position: relative;
  }
  .background{
    height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    filter: blur(1px);
  }
  .login{
    width: 400px;
    height: 700px;
    background: white;
    border-radius: 15px;
    border: 1px solid #AEAEAE;
    position: absolute;
    z-index: 2;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    box-shadow: 2px 2px 20px #888;
  }
  .title{
    text-align: center;
    font-weight: normal;
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  }
  .inputs{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction:column;
  }
  .input{
    margin-top: 10px;
    width: 200px;
    float: none;
    overflow: visible;
  }
  .label{
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    text-align: left;
    margin-right: 20px;
  }
  .submit{
    margin-top: 10px;
    width: 70%;
  }
  .register-link{
    margin-top: 10px;
    margin-left: 76%;
  }
</style>
