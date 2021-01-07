import method from '@/api/base'
import qs from 'qs'

const doneRegister = {
  done (regCode) {
    let params = qs.stringify({
      regCode: regCode
    })
    console.log(params)
    return method.post('/doneRegister', params)
  }
}

export default doneRegister
