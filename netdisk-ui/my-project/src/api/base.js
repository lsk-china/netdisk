import _axios from 'axios'
import common from '@/components/common'

_axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

const axios = _axios.create({
  baseURL: 'http://localhost:9001',
  timeout: 10000,
  withCredentials: true
})

const methods = {
  axios: axios,
  get (url, param) {
    return axios.get(url, {
      param
    })
  },
  post (url, param, callback) {
    console.log('Post: ' + url)
    console.log(param)
    let resp = ''
    axios.post(url, param, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then((response) => {
      console.log(response)
      common.resp = response
    })
    return resp
  }
}

export default methods
