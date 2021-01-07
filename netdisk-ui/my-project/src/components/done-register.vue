<template>
  <div class="mainContainer">
    <img class="background" src="../assets/login/background.jpg"/>
    <div class="login">
      <h1 class="title">请输入注册码</h1>
      <div class="inputs">
        <div>
          <span class="label"></span>
          <el-input class="input" v-model="registerCode"></el-input><br>
        </div>
        <el-button class="submit" type="primary" @click="done()">完成</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import doneRegister from '@/api/done_register'
export default {
  name: 'login',
  data () {
    return {
      registerCode: ''
    }
  },
  methods: {
    done () {
      let resp = doneRegister.done(this.registerCode)
      console.log(resp)
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
