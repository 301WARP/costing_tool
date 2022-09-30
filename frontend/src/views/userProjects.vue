<template>
  <div>
    <v-app>
      <!-- <v-div> -->
        <v-card>
          <v-row class="pa-15 mt-1">
            <h2 class="mx-auto">Projects</h2>
          </v-row>
          <v-card-title>
            <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details>
            </v-text-field>
          </v-card-title>
          <v-data-table :headers="headers" :items="projectListFixed" :search="search" @click:row="change()">
          </v-data-table>
          <v-row class="py-5">
            <v-col cols="4" align="center">
              <v-btn @click="router.push('/details')" color="primary" elevation="4" outlined large>Export Data
              </v-btn>
            </v-col>
            <v-col cols="4" align="center">
              <v-btn color="primary" elevation="4" outlined large>Edit Projects</v-btn>
            </v-col>
            <v-col cols="4" align="center">
              <v-btn @click="change()" color="primary" elevation="4" outlined large>Create Project</v-btn>
            </v-col>
          </v-row>
        </v-card>
      <!-- </v-div> -->
    </v-app>
  </div>
</template>
<script>
const axios = require("axios").default;
import router from "../router";

export default {
  data() {
    return {
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          filterable: true,
          value: "name",
        },
        { text: "Role", value: "role" },
        { text: "Contract", value: "contract" },
      ],
      projectList: [],
      projectListFixed: [],
    };
  },
  methods: {
    say(e) {
      alert(e);
    },
    load_projects_list() {
      axios.get("http://10.36.241.204:8080/api/projects/").then((resp) => {
        console.log(resp.data); //use resp.data[0].name for arrays
        this.researcher_list = resp.data;
        var obj;
        for (var i = 0; i < this.researcher_list.length; i++) {
          obj = {
            name: this.researcher_list[i].name,
            role: "CI",
            contract: "Full Time",
          };
          this.projectListFixed.push(obj);
        }
        console.log(this.projectListFixed);
      });
    },
    change() {
      router.push("/details");
    },
  },
  mounted() {
    this.load_projects_list();
  },
};
</script>
<style>
/* .v-btn {
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  /* margin-left: 250px; */
/* }  */
</style>
