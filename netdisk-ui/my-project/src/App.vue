<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
<style>
  html,body{
    height: 100%;
    width: 100%;
    overflow: hidden;
    margin: 0px;
    padding: 0px;
  }
  #app{
    height: 100%;
    width: 100%;
  }
</style>
