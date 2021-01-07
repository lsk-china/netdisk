package com.lsk.netdisk.auth.authz;

public enum Permission {
	Admin(2),User(1),Guest(0);

	private int p;

	Permission(int p) {
		this.p = p;
	}
	public boolean hasPermission(Permission permission){
		return this.p >= permission.p;
	}
}
