import { SENTENCES } from '../assets/sentences/index'

export class LAWDocument {
  constructor () {
    this.id = SENTENCES.DOC.mock_id
    this.title = SENTENCES.DOC.mock_title
    this.summary = SENTENCES.DOC.mock_summary
    this.content = SENTENCES.DOC.mock_content
  }
}
