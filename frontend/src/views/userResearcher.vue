<template>
  <v-app>
    <v-container>
      <v-subheader>Initial</v-subheader>
      <v-switch v-model="initial" inset></v-switch>
      <v-subheader>Casual</v-subheader>
      <v-switch v-model="casual" inset></v-switch>
      <v-subheader>RHD</v-subheader>
      <v-switch v-model="rhd" inset></v-switch>

      <div v-if="initial">
        <v-row>
          <v-col cols="2">
            <v-subheader>Title: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Title"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Family Name: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Given Names: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Role: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Contract: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-btn color="primary" elevation="4" outlined x-large>Submit</v-btn>
      </div>

      <div v-if="casual">
        <v-row>
          <v-col cols="2">
            <v-subheader>Title: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Title"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Family Name: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Given Names: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Role: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Contract: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Staff Type: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Classification: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Step: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Starting Salary: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Wage Adjustment: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Salary on Cost Rate: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>In Kind Contribution (%): </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-btn color="primary" elevation="4" outlined x-large>Submit</v-btn>
      </div>

      <div v-if="rhd">
        <v-row>
          <v-col cols="2">
            <v-subheader>Title: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Title"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Family Name: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Given Names: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Role: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Contract: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Classification: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="title" label="Select Contract"></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Annual Salary: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>In Kind Contribution (%): </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>
        <v-btn color="primary" elevation="4" outlined x-large>Submit</v-btn>
      </div>
    </v-container>
    <template>
      <v-data-table
        :headers="headers"
        :items="projects"
        :search="search"
        :single-expand="true"
        item-key="name"
        show-expand
        @click="say(test)"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>Staff</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>
        <template v-slot:expanded-item="{ headers, item }">
          <td :colspan="headers.length">More info about {{ item.name }}</td>
        </template>
      </v-data-table>
    </template>
  </v-app>
</template>

<script>
export default {
  data: () => ({
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
      { text: "Cash Income", value: "cash_income" },
      { text: "In Kind Contribution", value: "in_kind" },
      { text: "Actual Cost", value: "actual_cost" },
    ],
    projects: [
      {
        name: "Professor Jane Citizen",
        role: "CI",
        contract: "Full Time",
        cash_income: "$100,432",
        in_kind: "$100,432 (100%)",
        actual_cost: "$100,432 ",
      },
      {
        name: "RA - TBA",
        role: "RA",
        contract: "Full Time",
        cash_income: "$100,432",
        in_kind: "$100,432 (100%)",
        actual_cost: "$100,432",
      },
      {
        name: "Lab Assistant - TBA",
        role: "CI",
        contract: "Casual",
        cash_income: "$100,432 ",
        in_kind: "$100,432 (100%)",
        actual_cost: "$100,432",
      },
      {
        name: "Joe Bloggs",
        role: "PhD candidate",
        contract: "RHD",
        cash_income: "$100,432",
        in_kind: "$100,432 (100%)",
        actual_cost: "$100,432",
      },
    ],
    initial: false,
    casual: false,
    rhd: false,
  }),
  methods: {
    say(message) {
      alert(message);
    },
  },
};
</script>
