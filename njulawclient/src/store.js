import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const getters = {
  kw: state => state.kw,
  isSearch: state => state.isSearch,
  searchResult: state => state.searchTuplesAndKind
}

export default new Vuex.Store({
  state: {
    isSearch: true,
    kw: '',
    searchTuplesAndKind: []
  },
  mutations: {
    toSearch (state) {
      state.isSearch = true
    },
    notSearch (state) {
      state.isSearch = false
    },
    keyword (state, value) {
      state.kw = value
    },
    searchResult (state, value) {
      state.searchTuplesAndKind = value
    }
  },
  actions: {

  },
  getters
})
