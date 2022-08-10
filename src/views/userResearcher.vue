<template>
  <v-app>
    <v-container>
      <!-- <v-subheader>Initial</v-subheader>
      <v-switch v-model="initial" inset></v-switch>
      <v-subheader>Casual</v-subheader>
      <v-switch v-model="casual" inset></v-switch>
      <v-subheader>RHD</v-subheader>
      <v-switch v-model="rhd" inset></v-switch> -->

      <v-row>
        <v-col cols="2">
          <v-subheader>Title: </v-subheader>
        </v-col>
        <v-col cols="7">
          <v-col class="d-flex" sm="3">
            <v-select
              :items="title"
              label="Select Title"
              v-model="title_input"
            ></v-select>
          </v-col>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-subheader>Family Name: </v-subheader>
        </v-col>
        <v-col cols="2">
          <v-text-field v-model="family_name"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-subheader>Given Names: </v-subheader>
        </v-col>
        <v-col cols="2">
          <v-text-field v-model="given_name"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-subheader>Role: </v-subheader>
        </v-col>
        <v-col cols="2">
          <v-text-field v-model="role_input"></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="2">
          <v-subheader>Contract: </v-subheader>
        </v-col>
        <v-col cols="7">
          <v-col class="d-flex" sm="3">
            <v-select
              :items="contract"
              label="Select Contract"
              @change="checkContract($event)"
              v-model="contract_input"
            ></v-select>
          </v-col>
        </v-col>
      </v-row>

      <div v-if="full_time">
        <v-row>
          <v-col cols="2">
            <v-subheader>Staff Type: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="staff_type"
                label="Select Staff Type"
                v-model="staff_type_full"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Classification: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="classification"
                label="Select Classification"
                v-model="classification_full"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Step: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="step"
                label="Select Step"
                v-model="step_full"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Starting Salary: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="start_salary_full"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Wage Adjustment: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="wage_adj_full"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Salary on Cost Rate: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="salary_cost_rate"
                label="Select Salary on Cost Rate"
                v-model="salary_rate_full"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>In Kind Contribution (%): </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="in_kind_full"></v-text-field>
          </v-col>
        </v-row>
        <v-btn
          color="primary"
          elevation="4"
          outlined
          x-large
          @click="submit('NON_CASUAL')"
          >Submit</v-btn
        >
      </div>

      <div v-if="casual">
        <v-row>
          <v-col cols="2">
            <v-subheader>Staff Type: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="staff_type"
                label="Select Staff Type"
                v-model="staff_type_casual"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Classification: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="classification"
                label="Select Classification"
                v-model="classification_casual"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Pay Code: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="pay_code"
                label="Select Pay Code"
                v-model="pay_code_casual"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Starting Salary: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="start_salary_casual"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Wage Adjustment: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="wage_adj_casual"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Salary on Cost Rate: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="salary_cost_rate"
                label="Select Salary on Cost Rate"
                v-model="salary_rate_casual"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>In Kind Contribution (%): </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="in_kind_casual"></v-text-field>
          </v-col>
        </v-row>
        <v-btn
          color="primary"
          elevation="4"
          outlined
          x-large
          @click="submit('CASUAL')"
          >Submit</v-btn
        >
      </div>

      <div v-if="rhd">
        <v-row>
          <v-col cols="2">
            <v-subheader>Classification: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="classification"
                label="Select Classification"
                v-model="classification_rhd"
              ></v-select>
            </v-col>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Annual Salary: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="annual_salary_rhd"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>In Kind Contribution (%): </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field v-model="in_kind_rhd"></v-text-field>
          </v-col>
        </v-row>
        <v-btn
          color="primary"
          elevation="4"
          outlined
          x-large
          @click="submit('RHD')"
          >Submit</v-btn
        >
      </div>
    </v-container>
    <template>
      <v-data-table
        :headers="headers"
        :items="researcherFixed_list"
        :search="search"
        :single-expand="true"
        item-key="loop1"
        show-expand
        @click:row="fill_data($event)"
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
const axios = require("axios").default;

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
      { text: "In Kind Contribution", value: "inKindPercent" },
      { text: "Actual Cost", value: "actualCost" },
    ],
    researcher_list: [],
    researcherFixed_list: [],
    title: ["Dr", "Mr", "Mrs", "Ms"],
    contract: ["RHD", "NON_CASUAL", "CASUAL"],
    staff_type: ["ACADEMIC", "PROFESSIONAL"],
    classification: ["A", "RA1", "APA"],
    step: [1, 2, 3, 4, 5],
    salary_cost_rate: [1.0, 123.0],
    pay_code: [1, 2, 3, 4, 5],
    casual: false,
    rhd: false,
    full_time: false,

    title_input: "",
    family_name: "",
    given_name: "",
    role_input: "",
    contract_input: "",

    staff_type_full: "",
    classification_full: "",
    step_full: "",
    start_salary_full: "",
    wage_adj_full: "",
    salary_rate_full: "",
    in_kind_full: "",

    staff_type_casual: "",
    classification_casual: "",
    pay_code_casual: "",
    start_salary_casual: "",
    wage_adj_casual: "",
    salary_rate_casual: "",
    in_kind_casual: "",

    classification_rhd: "",
    annual_salary_rhd: "",
    in_kind_rhd: "",
  }),
  methods: {
    say(e) {
      alert(e);
    },
    checkContract(e) {
      if (e == "NON_CASUAL") {
        this.casual = false;
        this.rhd = false;
        this.full_time = true;
      } else if (e == "CASUAL") {
        this.casual = true;
        this.rhd = false;
        this.full_time = false;
      } else if (e == "RHD") {
        this.casual = false;
        this.rhd = true;
        this.full_time = false;
      } else {
        this.casual = false;
        this.rhd = false;
        this.full_time = false;
      }
    },
    update(e) {
      console.log("UPDATED DB WITH " + e.target.value);
    },
    fill_data(e) {
      this.title_input = e.title;
      this.family_name = e.lastName;
      this.given_name = e.firstName;
      this.role_input = e.role;
      this.contract_input = e.contract;
      console.log("?" + e);

      axios
        .get("http://10.36.241.204:8080/api/researchers/1/" + e.contractID)
        .then((resp) => {
          console.log(resp.data); //use resp.data[0].name for arrays
          this.researcher_list = resp.data;
          if (e.contract == "NON_CASUAL") {
            this.checkContract("NON_CASUAL");
            this.staff_type_full = resp.data.staffType;
            this.classification_full = resp.data.classification;
            this.step_full = resp.data.step;
            this.start_salary_full = resp.data.startingSalary;
            this.wage_adj_full = resp.data.wageAdjustment;
            this.salary_rate_full = resp.data.salaryOnCostRate;
            this.in_kind_full = e.inKindPercent;
          } else if (e.contract == "CASUAL") {
            this.checkContract("CASUAL");
            this.staff_type_casual = resp.data.staffType;
            this.classification_casual = resp.data.classification;
            this.pay_code_casual = resp.data.payCode;
            this.start_salary_casual = resp.data.hourlyRate;
            this.wage_adj_casual = resp.data.wageAdjustment;
            this.salary_rate_casual = resp.data.salaryOnCostRate;
            this.in_kind_casual = e.inKindPercent;
          } else if (e.contract == "RHD") {
            this.checkContract("RHD");
            this.classification_rhd = resp.data.classification;
            this.annual_salary_rhd = resp.data.wageExpense;
            this.in_kind_rhd = e.inKindPercent;
          }
        });
    },
    load_researcher_list() {
      axios.get("http://10.36.241.204:8080/api/researchers/1").then((resp) => {
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
    submit(contract) {
      if (contract == "NON_CASUAL") {
        console.log(
          "UPDATING RESEARCHER WITH " +
            this.family_name +
            " " +
            this.given_name +
            " " +
            this.role_input +
            " " +
            this.contract_input +
            " " +
            this.staff_type_full +
            " " +
            this.classification_full +
            " " +
            this.step_full +
            " " +
            this.start_salary_full +
            " " +
            this.wage_adj_full +
            " " +
            this.salary_rate_full +
            " " +
            this.in_kind_full
        );
      } else if (contract == "CASUAL") {
        console.log(
          "UPDATING RESEARCHER WITH " +
            this.family_name +
            " " +
            this.given_name +
            " " +
            this.role_input +
            " " +
            this.contract_input +
            " " +
            this.staff_type_casual +
            " " +
            this.classification_casual +
            " " +
            this.pay_code_casual +
            " " +
            this.start_salary_casual +
            " " +
            this.wage_adj_casual +
            " " +
            this.salary_rate_casual +
            " " +
            this.in_kind_casual
        );
      } else if (contract == "RHD") {
        console.log(
          "UPDATING RESEARCHER WITH " +
            this.family_name +
            " " +
            this.given_name +
            " " +
            this.role_input +
            " " +
            this.contract_input +
            " " +
            this.classification_rhd +
            " " +
            this.annual_salary_rhd +
            " " +
            this.in_kind_rhd
        );
      }
    },
  },
  mounted() {
    this.load_researcher_list();
  },
};
</script>
