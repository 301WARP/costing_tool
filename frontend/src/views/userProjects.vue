<template>
  <div>
    <v-app>
      <v-card>
        <br /><br /><br /><br />
        <h2>Projects</h2>
        <v-card-title>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </v-card-title>
        <v-data-table
          :headers="headers"
          :items="projects"
          :search="search"
        ></v-data-table>
        <v-btn @click="say('test')" color="primary" elevation="4" outlined large
          >Create Project</v-btn
        >
        <v-btn @click="q" color="primary" elevation="4" outlined large
          >Edit Projects</v-btn
        >
        <v-btn @click="q" color="primary" elevation="4" outlined large
          >Create Project</v-btn
        >
      </v-card>
    </v-app>
  </div>
</template>
<script>
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
      projects: [
        {
          name: "Tasmanian Abalone Fishery Assessment",
          role: "CI",
          contract: "Full Time",
        },
        {
          name: "Assessment of ...",
          role: "RA",
          contract: "Full Time",
        },
        {
          name: "Report of ...",
          role: "CI",
          contract: "Full Time",
        },
      ],
    };
  },
  methods: {
    say(message) {
      alert(message);
    },
  },
};
const axios = require("axios").default;

axios.get("http://10.36.241.204:8080/api/projects").then((resp) => {
  console.log(resp.data);
});
</script>
<style>
.v-btn {
  margin-top: 50px;
  margin-left: 250px;
}
</style>
