import methods from '@/api/base'

const register = {
  register (param) {
    let result = ''
    methods.post('/register', param, (resp) => {
      console.log(resp.data)
      result = resp.data
    })
    return result
  },
  isUserExists (username) {
    return methods.get('/isUserExists', {
      username: username
    })
  }
}

export default register
