import { LAWDocument } from '../../types/LAWDocument'

export const searchDoc = async (kw) => {
  let docList = []
  for (let i = 0; i < 10; i++) {
    docList.push(new LAWDocument())
  }
  return docList
}

export const getDocContent = async (docId) => {
  return new LAWDocument()
}

const MockUserApi = {
  searchDoc,
  getDocContent
}

export default MockUserApi
