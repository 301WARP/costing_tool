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
          @click:row="say($event)"
        ></v-data-table>
        <v-btn @click="say('test')" color="primary" elevation="4" outlined large
          >Create Project</v-btn
        >
        <v-btn color="primary" elevation="4" outlined large
          >Edit Projects</v-btn
        >
        <v-btn color="primary" elevation="4" outlined large
          >Create Project</v-btn
        >
      </v-card>
    </v-app>
  </div>
</template>
<script>
const axios = require("axios").default;

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
      projectList: [],
      projectListFixed: [],
    };
  },
  methods: {
    say(e) {
      alert(e);
    },
  },
  load_researcher_list() {
    axios.get("http://10.36.241.204:8080/api/projects/").then((resp) => {
      console.log(resp.data); //use resp.data[0].name for arrays
      this.researcher_list = resp.data;
      var obj;
      for (var i = 0; i < this.researcher_list.length; i++) {
        obj = {
          title: this.researcher_list[i].title,
          name:
            this.researcher_list[i].firstName +
            " " +
            this.researcher_list[i].lastName,
          firstName: this.researcher_list[i].firstName,
          lastName: this.researcher_list[i].lastName,
          staffID: this.researcher_list[i].staffID,
          role: this.researcher_list[i].role,
          contract: this.researcher_list[i].contract,
          contractID: this.researcher_list[i].contrtactID,
          cash_income: "$" + 0,
          inKindPercent: "$" + this.researcher_list[i].inKindPercent,
          actualCost: "$" + this.researcher_list[i].actualCost,
        };
        this.researcherFixed_list.push(obj);
      }
      console.log(this.researcherFixed_list);
    });
  },
};
</script>
<style>
.v-btn {
  margin-top: 50px;
  margin-left: 250px;
}
</style>
