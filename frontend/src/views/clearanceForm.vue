<template>
  <v-app>
    <v-row style="position: absolute; top: 50px; right: 50px">
      <v-row style="text-align: right">
        <v-btn @click="exportPDF()" style="float: right">Export</v-btn>
      </v-row>
    </v-row>
    <v-container id="element-to-convert">
      <v-row class="pa-15">
        <h2 class="mx-auto">Research Funding Clearance Form</h2>
      </v-row>

      <v-row class="px-5 my-5">
        <v-card
          outlined
          elevation="5"
          width="100%"
          class="pa-0 mx-auto"
          max-width="970px"
          style="border: 1px solid darkblue"
        >
          <v-card-title>Project Details</v-card-title>
          <v-card-actions>
            <v-col cols="12" class="my-0 py-0">
              <v-text-field
                label="Project title"
                class="my-0 py-0"
                value="Test Project"
              ></v-text-field>
            </v-col>
          </v-card-actions>
          <v-card-actions>
            <v-col cols="12" class="my-0 py-0">
              <v-textarea
                outlined
                width="100%"
                height="100px"
                label="Brief project description"
              ></v-textarea>
            </v-col>
          </v-card-actions>
        </v-card>
      </v-row>

      <v-row class="px-5 my-5" width="100%">
        <v-card
          outlined
          elevation="5"
          width="100%"
          max-width="970px"
          class="mx-auto"
          style="border: 1px solid darkblue"
        >
          <v-card-title class="mx-auto"
            >HERDC research requirements</v-card-title
          >
          <v-card-text
            >Does the project meet the
            <a
              href="https://www.utas.edu.au/research-admin/research-funding/finding-and-applying-for-funding/definition-of-research"
              >five core criteria</a
            >? Please explain how:</v-card-text
          >
          <v-card-actions class="px-3">
            <v-col cols="12">
              <v-textarea outlined width="100%"></v-textarea>
            </v-col>
          </v-card-actions>
        </v-card>
      </v-row>

      <v-row class="px-5 my-0 py-0">
        <v-card
          outlined
          elevation="5"
          max-width="970px"
          class="mx-auto py-0"
          width="100%"
          style="border: 1px solid darkblue"
        >
          <v-row>
            <v-col cols="12">
              <v-card-actions class="py-0 my-0">
                <v-col cols="6">
                  <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :return-value.sync="date"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="date"
                        label="Proposed commencement date"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        disabled
                        value="2022-09-06"
                      >
                      </v-text-field>
                    </template>
                    <v-date-picker v-model="date" no-title scrollable>
                      <v-spacer></v-spacer>
                      <v-btn text color="primary" @click="menu = false">
                        Cancel
                      </v-btn>
                      <v-btn
                        text
                        color="primary"
                        @click="$refs.menu.save(date)"
                      >
                        OK
                      </v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-col>
                <v-col cols="6">
                  <v-menu
                    ref="menu2"
                    v-model="menu2"
                    :close-on-content-click="false"
                    :return-value.sync="date2"
                    transition="scale-transition"
                    offset-y
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="date2"
                        label="Proposed completion date"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        disabled
                        value="2023-03-17"
                      >
                      </v-text-field>
                    </template>
                    <v-date-picker v-model="date2" no-title scrollable>
                      <v-spacer></v-spacer>
                      <v-btn text color="primary" @click="menu2 = false">
                        Cancel
                      </v-btn>
                      <v-btn
                        text
                        color="primary"
                        @click="$refs.menu2.save(date2)"
                      >
                        OK
                      </v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-col>
              </v-card-actions>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-card-actions class="py-0 my-0">
                <v-col cols="6">
                  <v-text-field
                    label="Funding Body / Client"
                    outlined
                    disabled
                  ></v-text-field>
                </v-col>
                <v-col cols="6">
                  <v-text-field label="Scheme" outlined></v-text-field>
                </v-col>
              </v-card-actions>
            </v-col>
          </v-row>
        </v-card>
      </v-row>

      <v-row class="px-5 my-5">
        <v-card
          outlined
          elevation="5"
          width="100%"
          class="pa-0 mx-auto"
          max-width="970px"
          style="border: 1px solid darkblue"
        >
          <v-card-title>Lead Investigator - UTAS or external</v-card-title>
          <v-card-actions>
            <v-col cols="4">
              <v-text-field label="Name"></v-text-field>
            </v-col>
            <v-col cols="6">
              <v-text-field
                label="UTAS discipline/unit or External Organisation"
              ></v-text-field>
            </v-col>
            <v-col cols="2">
              <v-text-field label="FTE %"></v-text-field>
            </v-col>
          </v-card-actions>
          <v-divider></v-divider>

          <v-card-title class="pb-2"
            >All Other Investigators (incl. students)</v-card-title
          >
          <v-card-actions v-for="(item, i, index) in 3" :key="index">
            <v-col cols="4" class="py-0">
              <v-text-field label="Name" class="py-0"></v-text-field>
            </v-col>
            <v-col cols="6" class="py-0">
              <v-text-field
                label="UTAS discipline/unit or External Organisation"
                class="py-0"
              >
              </v-text-field>
            </v-col>
            <v-col cols="2" class="py-0">
              <v-text-field label="FTE %" class="py-0"></v-text-field>
            </v-col>
          </v-card-actions>
          <v-divider></v-divider>

          <v-row>
            <v-col cols="7">
              <v-card-title class="pb-0"
                >Higher Degree Research students</v-card-title
              >
            </v-col>
            <v-col class="mt-5 mr-5" style="text-align: right">
              <i style="color: grey"
                >A <a href="">Deed of Assignment</a> may be required</i
              >
            </v-col>
          </v-row>
          <v-card-actions>
            <v-col cols="6">
              <v-text-field label="Name" class="py-0"></v-text-field>
            </v-col>
            <v-col cols="6">
              <v-text-field
                label="UTAS discipline/unit or External Organisation"
                class="py-0"
              >
              </v-text-field>
            </v-col>
          </v-card-actions>
          <v-card-actions class="py-0">
            <v-col cols="3" class="py-0">
              <v-subheader>Type of involvement:</v-subheader>
            </v-col>
            <v-col cols="3" class="py-0">
              <v-checkbox
                v-model="checkbox"
                label="Associated with project / unpaid"
                class="py-0"
              >
              </v-checkbox>
            </v-col>
            <v-col cols="3" class="py-0">
              <v-checkbox
                v-model="checkbox"
                label="Paid from project - casual employee"
                class="py-0"
              >
              </v-checkbox>
            </v-col>
            <v-col cols="3" class="py-0">
              <v-checkbox
                v-model="checkbox"
                label="Scholarship paid from project"
                class="py-0"
              >
              </v-checkbox>
            </v-col>
          </v-card-actions>
        </v-card>
      </v-row>

      <v-row class="px-5 my-5">
        <v-card
          outlined
          elevation="5"
          width="100%"
          class="pa-0 mx-auto"
          max-width="970px"
          style="border: 1px solid darkblue"
        >
          <v-card-title>Research Codes</v-card-title>
          <v-row>
            <v-col cols="4" class="">
              <v-row>
                <v-card-actions>
                  <v-col cols="10">
                    <v-card-subtitle>Field of Research*</v-card-subtitle>
                  </v-col>
                  <v-col cols="2">
                    <v-card-subtitle>%</v-card-subtitle>
                  </v-col>
                </v-card-actions>
              </v-row>
            </v-col>

            <v-col cols="4">
              <v-row>
                <v-col cols="9">
                  <v-row>
                    <p>Socio-Economic Objective*</p>
                  </v-row>
                </v-col>
                <v-col cols="3">
                  <p>%</p>
                </v-col>
              </v-row>
            </v-col>

            <v-col cols="4">
              <v-row>
                <v-col cols="9">
                  <v-row>
                    <p>Type(s) of Activity*</p>
                  </v-row>
                </v-col>
                <v-col cols="3">
                  <p>%</p>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-card>
      </v-row>

      <v-row class="px-5 my-5">
        <v-card
          outlined
          elevation="5"
          width="100%"
          class="pa-0 mx-auto"
          max-width="970px"
          style="border: 1px solid darkblue"
        >
          <v-card-title
            >Ethics / Biosafety / WHS / Clinical trials</v-card-title
          >
          <v-card-subtitle
            >You must ensure that no activity that requires ethics approval
            commences without that approval being granted by the relevant ethics
            committee.</v-card-subtitle
          >
          <v-row>
            <v-row>
              <v-col cols="12">
                <h4>
                  <v-card-subtitle>Does your research involve:</v-card-subtitle>
                </h4>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="6">
                <p>Human participants (medical or social science)</p>
              </v-col>
              <v-col cols="6">
                <v-checkbox v-model="checkbox" label="Yes"></v-checkbox>
                <v-checkbox v-model="checkbox" label="No"></v-checkbox>
              </v-col>
            </v-row>
          </v-row>
        </v-card>
      </v-row>
    </v-container>
  </v-app>
</template>
<script>
//const axios = require("axios").default;
import html2pdf from "html2pdf.js";
// const axios = require("axios").default;

export default {
  data() {
    return {
      title: "Test Project",
    };
  },
  methods: {
    exportPDF() {
      html2pdf(document.getElementById("element-to-convert"), {
        margin: 5,
        filename: "Research-Funding-Clearance-Form.pdf",
        html2canvas: { scale: 5 },
      });
    },
  },
  // load_clearance_form() {
  //   axios
  //     .get(
  //       "http://10.36.241.204:8080/api/clearance/" +
  //         this.$store.state.projectIndex
  //     )
  //     .then((resp) => {
  //       console.log(resp.data); //use resp.data[0].name for arrays
  //       this.costListTemp = resp.data;
  //       var obj;
  //       for (var i = 0; i < this.costListTemp.length; i++) {
  //         obj = {
  //           id: this.costListTemp[i].id,
  //           type: this.costListTemp[i].type,
  //           description: this.costListTemp[i].description,
  //           in_kind: this.costListTemp[i].inKindPercent,
  //           actual_cost: this.costListTemp[i].actualCost,
  //         };
  //         this.costListFixed.push(obj);
  //       }
  //     });
  // },
  mounted() {
    if (this.$store.state.projectIndex == -1) {
      this.$router.push("/");
    }
    window.scrollTo(0, 0);
  },
};
</script>
