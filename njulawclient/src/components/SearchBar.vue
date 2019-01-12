/* eslint-disable */

<template>
    <layout class="searchs">
      <Row>
        <Col span="18" style="text-align: right">
          <Input :placeholder="searchBar"
                 style="border-radius: 10px;border: 1px;width:80%;font-size: 24px;font-weight: bold"
                 v-on:input="changeValue"
                 @keyup.enter.native="startSearch" title="search" size="large"/>
        </Col>
        <Col span="6" style="text-align: left;vertical-align: center;padding-left:10px;padding-top: 1px">
          <Button type="primary" style="display: inline-block" icon="ios-search" size="large"
                  @click.native="startSearch">搜索
          </Button>
        </Col>
      </Row>
    </layout>
</template>
<script>
import { SENTENCES } from '../assets/sentences/index'
export default {
  data () {
    return {
      searchBar: SENTENCES.SEARCH.TITLE,
      kw: ''
    }
  },
  methods: {
    changeValue (value) {
      this.kw = value
    },
    startSearch () {
      console.log('key word in searchBar' + this.kw)
      this['$router'].push({ path: '/search/' + this.kw })
    }
  }
}
</script>
<style scoped>
  .searchs{
    background-color: transparent;
  }

</style>
