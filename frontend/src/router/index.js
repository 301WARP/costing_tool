import Vue from "vue";
import VueRouter from "vue-router";
import Projects from "../views/userProjects.vue";
import Details from "../views/userDetails.vue";
import Researcher from "../views/userResearcher.vue";
import Clearance from "../views/clearanceForm.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "projects",
    component: Projects,
  },
  {
    path: "/details",
    name: "Details",
    component: Details,
  },
  {
    path: "/researcher",
    name: "Researcher",
    component: Researcher,
  },
  {
    path: "/clearance",
    name: "Clearance",
    component: Clearance,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
