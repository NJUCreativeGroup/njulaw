// 这是写user的api

import MockUserApi from './mock/api'

import { isTestMode } from './http-service'
import http from './http-service'

export const searchDoc = async (kw) => {
  console.log(isTestMode)
  if (isTestMode) {
    console.log('mock')
    return MockUserApi.searchDoc(kw)
  } else {
    console.log('real')
    return http.post('/tbd', { kw })
  }
}

export const getDocContent = async (docId) => {
  if (isTestMode) {
    return MockUserApi.getDocContent()
  } else {
    return http.post('/tbd', { docId })
  }
}
