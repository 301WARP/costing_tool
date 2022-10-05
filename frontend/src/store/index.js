import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    projectIndex: -1,
  },
  getters: {},
  mutations: {
    setProjectIndex(state, index) {
      state.projectIndex = index;
    },
  },
  actions: {},
  modules: {},
});
