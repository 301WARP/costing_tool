import Vue from "vue";
import VueRouter from "vue-router";
import Projects from "../views/userProjects.vue";
import Details from "../views/userDetails.vue";
import Researcher from "../views/userResearcher.vue";
import Clearance from "../views/clearanceForm.vue";
import Costs from "../views/userCosts.vue";

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
  {
    path: "/costs",
    name: "Costs",
    component: Costs,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
