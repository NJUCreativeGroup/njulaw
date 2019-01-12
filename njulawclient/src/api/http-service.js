import Axios from 'axios'
import { Logger } from '../utils/Logger'

Axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8'

export const HttpMethod = {
  GET: 'GET',
  POST: 'POST',
  DELETE: 'DELETE',
  PATCH: 'PATCH',
  PUT: 'PUT'
}

export const isTestMode = true

export const baseURL = ''

export const mockHttp = (data) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(data)
    }, 1000)
  })
}
/*  */

class HttpService {
  constructor () {
    this._instance = Axios.create({
      baseURL: baseURL,
      withCredentials: true
      // baseURL:'http://127.0.0.1:8082'
    })
    this._TAG = 'HttpService'
  }

  async get (path, params) {
    Logger.info('api', `请求${path}`, params)
    try {
      let res = await this._instance.get(path, {
        params: params
      })
      Logger.info(this._TAG, '请求得到', res)
      if (res.status !== 200) {
        Logger.error(this._TAG, `请求错误，错误码：${res.status}`)
        throw new Error(`请求错误码: ${res.status}`)
      }
      return res.data
    } catch (e) {
      throw e
    }
  }

  async post (path, params) {
    Logger.info('api', `请求${path}`, params)
    try {
      let data = new URLSearchParams()
      for (let key in params) {
        data.append(key, params[key])
      }
      let res = await this._instance.post(path, data)
      Logger.info(this._TAG, '请求得到', res)
      if (res.status !== 200) {
        Logger.error(this._TAG, `请求错误，错误码：${res.status}`)
        throw new Error(`请求错误码: ${res.status}`)
      }
      return res.data
    } catch (e) {
      throw e
    }
  }
}

const http = new HttpService()
export default http
