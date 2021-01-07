import methods from '@/api/base'

console.log(methods)

const login = {
  login (params) {
    return methods.post('/login', params)
  },
  getToken () {
    methods.get('/token')
    console.log('debug')
  }
}
export default login
