<template>
  <div class="mainContainer">
    <img class="background" src="../assets/login/background.jpg"/>
    <div class="login">
      <h1 class="title">请注册</h1>
      <div class="inputs">
        <div>
          <span class="label">用户名:</span>
          <el-input class="input" v-model="username"></el-input><br>
        </div>
        <div>
          <span class="label" style="margin-left: 5px;">密码：</span>
          <el-input class="input" type="password" v-model="password"></el-input>
        </div>
        <div>
          <span class="label" style="margin-right: 0px;">重复密码：</span>
          <el-input style="width: 200px" class="input" type="password" v-model="password_repeat" @input="checkPassword()"></el-input>
          <img :src="img" class="state-img"/>
        </div>
        <div>
          <span class="label" style="margin-left: 20px">邮箱：</span>
          <el-input style="width: 200px" class="input" type="text" v-model="email"></el-input>
        </div>
        <el-button class="submit" type="primary" @click="register()">注册</el-button>
      </div>
    </div>
  </div>
</template>
<script>
// import register from '@/api/register'
import login from '@/api/login'
import {getCookie} from '@/utils/cookieutil'
import qs from 'qs'
import methods from '@/api/base'

export default {
  name: 'register',
  data () {
    return {
      username: '',
      password: '',
      password_repeat: '',
      email: '',
      img_right: require('@/assets/register/dui.jpg'),
      img_wrong: require('@/assets/register/cuo.jpg'),
      img: '',
      isPasswordCorrect: false,
      regCode: ''
    }
  },
  methods: {
    register () {
      if (!this.isPasswordCorrect) {
        alert('密码不一致!')
        return
      }
      let username = this.username
      let password = this.password
      let email = this.email
      let params = qs.stringify({
        username: username,
        password: password,
        email: email
      })
      if (this.checkUserExists()) {
        return
      }
      methods.axios.post('/register', params).then((response) => {
        this.$copyText(response.data)
        this.$message.success('您的注册码为：' + response.data + '。5秒后将跳转到完成注册页面。')
      })
      setTimeout(() => {
        this.$router.push('/doneRegister')
      }, 10000)
    },
    checkPassword () {
      if (this.password === this.password_repeat) {
        this.img = this.img_right
        this.isPasswordCorrect = true
      } else {
        this.img = this.img_wrong
        this.isPasswordCorrect = false
      }
    },
    checkUserExists () {
      methods.axios.get('/isUserExists', {
        username: this.username
      }).then((response) => {
        if (response.data.data === false) {
          return false
        }
        this.$message.error('用户名已存在！')
        return true
      })
      return false
    }
  },
  created () {
    methods.get('/token')
    let cookie = getCookie('token')
    if (cookie === '') {
      login.getToken()
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
    width: 500px;
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
    width: 210px;
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
    width: 60%;
  }
  .register-link{
    margin-top: 10px;
    margin-left: 76%;
  }
</style>
