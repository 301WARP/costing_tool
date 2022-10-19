<template>
  <v-app>
    <v-row style="position: absolute; top: 50px; right: 50px">
      <v-row style="text-align: right">
        <v-btn @click="exportPDF()" style="float: right">Export</v-btn>
      </v-row>
    </v-row>
    <v-row id="no-margin-x">
      <v-container id="element-to-convert">
        <v-col
          cols="12"
          style="
            background: RGB(45, 136, 154);
            border: 3px solid black;
            color: white;
            text-align: center;
          "
        >
          <h2>Research Funding Clearance Form</h2>
        </v-col>
        <v-col cols="12" id="no-margin-x">
          <table class="border-all">
            <tr>
              <table>
                <tr id="b">
                  <th class="bg-blue" id="b">Project Details</th>
                </tr>
              </table>
            </tr>
            <tr>
              <table>
                <tr id="b">
                  <td id="b">
                    Grant - Cat 1
                    <input
                      type="checkbox"
                      id="form-box"
                      v-model="this.categories.one"
                    />
                  </td>
                  <td id="b">
                    Grant - Cat 2
                    <input
                      type="checkbox"
                      id="form-box"
                      v-model="this.categories.two"
                    />
                  </td>
                  <td id="b">
                    Grant - Cat 3
                    <input
                      type="checkbox"
                      id="form-box"
                      v-model="this.categories.three"
                    />
                  </td>
                  <td id="b">
                    Grant - Cat 4 (CRC/P)
                    <input
                      type="checkbox"
                      id="form-box"
                      v-model="this.categories.four"
                    />
                  </td>
                  <td id="b">
                    Contract Research
                    <input
                      type="checkbox"
                      id="form-box"
                      v-model="this.categories.contract"
                    />
                  </td>
                  <td id="b">
                    Consultancy
                    <input
                      type="checkbox"
                      id="form-box"
                      v-model="this.categories.consultancy"
                    />
                  </td>
                  <td id="b">
                    Tender
                    <input
                      type="checkbox"
                      id="form-box"
                      v-model="this.categories.tender"
                    />
                  </td>
                </tr>
              </table>
            </tr>
          </table>
          <table class="border-middle">
            <tr>
              <table>
                <tr>
                  <td>
                    <tr>
                      <th id="b">Project Title:</th>
                      <td id="be">{{ this.projectDetails.projectTitle }}</td>
                    </tr>
                  </td>
                </tr>
              </table>
            </tr>
          </table>
          <table class="border-middle">
            <tr>
              <table>
                <tr id="b">
                  <th id="b">Brief Project Description:</th>
                </tr>
                <tr id="b">
                  <td id="b">{{ this.projectDetails.description }}</td>
                </tr>
              </table>
            </tr>
          </table>
          <table class="border-middle">
            <tr>
              <table>
                <tr id="b">
                  <td id="b">
                    <b>HERDC research requirements</b>
                    - Does the project meet the
                    <a
                      href="https://www.utas.edu.au/research-admin/research-funding/finding-and-applying-for-funding/definition-of-research"
                      >five core criteria</a
                    >? Please explain how:
                  </td>
                </tr>
                <tr id="b">
                  <td id="b">
                    {{ this.projectDetails.herdc }}
                  </td>
                </tr>
              </table>
            </tr>
          </table>
          <table class="border-middle">
            <tr>
              <table>
                <tr>
                  <v-row id="no-spacing-a">
                    <v-col cols="6" id="no-spacing-a">
                      <td>
                        <tr>
                          <th id="b">Proposed commencement date:</th>
                          <td id="be">{{ this.projectDetails.startDate }}</td>
                        </tr>
                      </td>
                    </v-col>
                    <v-col cols="6" id="no-spacing-a">
                      <td>
                        <tr>
                          <th id="b">Proposed completion date:</th>
                          <td id="be">{{ this.projectDetails.endDate }}</td>
                        </tr>
                      </td>
                    </v-col>
                  </v-row>
                </tr>
              </table>
            </tr>
          </table>
          <table class="border-middle">
            <tr>
              <table>
                <tr>
                  <v-row id="no-spacing-a">
                    <v-col cols="6" id="no-spacing-a">
                      <td>
                        <tr>
                          <th id="b">Funding Body / Client:</th>
                          <td id="be">{{ this.projectDetails.fundingBody }}</td>
                        </tr>
                      </td>
                    </v-col>
                    <v-col cols="6" id="no-spacing-a">
                      <td>
                        <tr>
                          <th id="b">Scheme:</th>
                          <td id="be">{{ this.projectDetails.scheme }}</td>
                        </tr>
                      </td>
                    </v-col>
                  </v-row>
                </tr>
              </table>
            </tr>
          </table>
          <table class="border-middle">
            <tr>
              <table>
                <tr id="b">
                  <th id="b">
                    For contract research and consultancy only, provide a
                    contact name and email address
                  </th>
                </tr>
              </table>
            </tr>
            <tr>
              <table>
                <tr>
                  <v-row id="no-spacing-a">
                    <v-col cols="6" id="no-spacing-a">
                      <td>
                        <tr>
                          <th id="bnt">Contact name:</th>
                          <td id="bnte">
                            {{ this.projectDetails.contactName }}
                          </td>
                        </tr>
                      </td>
                    </v-col>
                    <v-col cols="6" id="no-spacing-a">
                      <td>
                        <tr>
                          <th id="bnt">Contact email:</th>
                          <td id="bnte">
                            {{ this.projectDetails.contactEmail }}
                          </td>
                        </tr>
                      </td>
                    </v-col>
                  </v-row>
                </tr>
              </table>
            </tr>
          </table>
        </v-col>
        <v-col cols="12" id="no-margin-x">
          <table class="border-all">
            <tr>
              <table>
                <tr id="b">
                  <td id="b" class="bg-blue">
                    <b>Lead Investigator - UTAS or external</b>
                    <i>
                      (If this is a student-led project, their supervisor must
                      be listed as the Lead Investigator)
                    </i>
                  </td>
                </tr>
              </table>
            </tr>
            <tr>
              <table>
                <tr>
                  <th id="bnte">Name:</th>
                  <th id="bnte">
                    UTAS discipline/unit <i>or</i> External Organisation:
                  </th>
                  <th id="bnte" height="100%">FTE %:</th>
                </tr>
                <tr>
                  <td id="bnte">{{ this.leadInvestigator.name }}</td>
                  <td id="bnte">
                    {{ this.leadInvestigator.organisation }}
                  </td>
                  <td id="bnte">{{ this.leadInvestigator.fte }}</td>
                </tr>
              </table>
            </tr>
          </table>
        </v-col>
      </v-container>
    </v-row>
    <v-container>
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
                :value="this.projectDetails.projectTitle"
                disabled
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
                v-model="projectDetails.description"
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
              <v-textarea
                outlined
                width="100%"
                v-model="projectDetails.herdc"
              ></v-textarea>
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
                  <v-text-field
                    v-model="this.projectDetails.startDate"
                    label="Proposed commencement date"
                    prepend-icon="mdi-calendar"
                    readonly
                    disabled
                    value="this.projectDetails.startDate"
                  ></v-text-field>
                </v-col>
                <v-col cols="6">
                  <v-text-field
                    v-model="this.projectDetails.endDate"
                    label="Proposed completion date"
                    prepend-icon="mdi-calendar"
                    readonly
                    disabled
                    value="this.projectDetails.endDate"
                  >
                  </v-text-field>
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
                    :value="projectDetails.fundingBody"
                  ></v-text-field>
                </v-col>
                <v-col cols="6">
                  <v-text-field
                    label="Scheme"
                    outlined
                    v-model="projectDetails.scheme"
                  ></v-text-field>
                </v-col>
              </v-card-actions>
            </v-col>
          </v-row>
          <v-divider></v-divider>
          <v-row class="ma-5 pt-0">
            <v-card-text class="mt-0 py-0">
              For contract research and consultancies only, provide a contact
              name and email address:
            </v-card-text>
            <v-col cols="6">
              <v-text-field
                label="Contact Name"
                v-model="projectDetails.contactName"
              ></v-text-field>
            </v-col>
            <v-col cols="6">
              <v-text-field
                label="Email Address"
                type="email"
                v-model="projectDetails.contactEmail"
              ></v-text-field>
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
              <v-text-field
                label="Name"
                v-model="leadInvestigator.name"
              ></v-text-field>
            </v-col>
            <v-col cols="6">
              <v-text-field
                label="UTAS discipline/unit or External Organisation"
                v-model="leadInvestigator.organisation"
              ></v-text-field>
            </v-col>
            <v-col cols="2">
              <v-text-field
                label="FTE %"
                v-model="leadInvestigator.fte"
              ></v-text-field>
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
                v-model="unpaid"
                label="Associated with project / unpaid"
                class="py-0"
              >
              </v-checkbox>
            </v-col>
            <v-col cols="3" class="py-0">
              <v-checkbox
                v-model="casual"
                label="Paid from project - casual employee"
                class="py-0"
              >
              </v-checkbox>
            </v-col>
            <v-col cols="3" class="py-0">
              <v-checkbox
                v-model="scholarship"
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
                <v-checkbox v-model="humanYes" label="Yes"></v-checkbox>
                <v-checkbox v-model="humanNo" label="No"></v-checkbox>
              </v-col>
            </v-row>
          </v-row>
        </v-card>
      </v-row>
    </v-container>
  </v-app>
</template>
<script>
import html2pdf from "html2pdf.js";
const axios = require("axios").default;

export default {
  data() {
    return {
      categories: {
        one: false,
        two: false,
        three: false,
        four: false,
        contract: false,
        consultancy: false,
        exemption: false,
        tender: false,
      },
      projectDetails: {
        category: "",
        projectTitle: "",
        description: "",
        herdc: "",
        startDate: Date,
        endDate: Date,
        fundingBody: "",
        scheme: "",
        contactName: "",
        contactEmail: "",
      },
      leadInvestigator: {
        name: "",
        organisation: "",
        fte: "",
      },
      investigators: {
        // array of:
        name: "",
        organisation: "",
        fte: "",
      },
      externalInvestigators: {
        // array of:
        name: "",
        organisation: "",
        fte: "",
      },
      rhdStudents: {
        // array of:
        name: "",
        unit: "",
        involvement: "",
      },
      researchCodes: {
        // array of:
        forCodes: {
          code: "",
          percentage: "",
        },
        // array of:
        seoCodes: {
          code: "",
          percentage: "",
        },
        appliedRearch: "",
        experimentalDevelopment: "",
        strategicBasic: "",
        pureBasic: "",
      },
      ethics: {
        human: false,
        humanRefNo: "",
        animal: false,
        animalRefNo: "",
        controlledDrugs: false,
        clinicalTrial: false,
      },
      costs: "",
      externalPayments: "",
      clearanceInfoTemp: "",
      clearanceInfo: "",
      menu: false,
      menu2: false,
      unpaid: false,
      casual: false,
      scholarship: false,
      humanYes: false,
      humanNo: false,
    };
  },
  methods: {
    exportPDF() {
      html2pdf(document.getElementById("element-to-convert"), {
        // margin: 1,
        filename: "Research-Funding-Clearance-Form.pdf",
        html2canvas: { scale: 7 },
      });
    },
    load_clearance_form() {
      axios
        .get(
          "http://10.36.241.204:8080/api/clearance/" +
            this.$store.state.projectIndex
        )
        .then((resp) => {
          console.log(resp.data); //use resp.data[0].name for arrays
          this.projectDetails.projectTitle =
            resp.data.projectDetails.projectTitle;
          this.projectDetails.category = resp.data.projectDetails.category;
          this.projectDetails.description =
            resp.data.projectDetails.description;
          this.projectDetails.herdc = resp.data.projectDetails.herdc;
          this.projectDetails.startDate = resp.data.projectDetails.startDate;
          this.projectDetails.endDate = resp.data.projectDetails.endDate;
          this.projectDetails.fundingBody =
            resp.data.projectDetails.fundingBody;
          this.projectDetails.scheme = resp.data.projectDetails.scheme;
          this.projectDetails.contactName =
            resp.data.projectDetails.contactName;
          this.projectDetails.contactEmail =
            resp.data.projectDetails.contactEmail;
          switch (resp.data.projectDetails.category) {
            case "ONE":
              this.categories.one = true;
              break;
            case "TWO":
              this.categories.two = true;
              break;
            case "THREE":
              this.categories.three = true;
              break;
            case "FOUR":
              this.categories.four = true;
              break;
            case "CONTRACT":
              this.categories.contract = true;
              break;
            case "CONSULTANCY":
              this.categories.consultancy = true;
              break;
            case "EXEMPTION":
              this.categories.exemption = true;
              break;
            case "TENDER":
              this.categories.tender = true;
              break;
          }
          this.leadInvestigator.name = resp.data.leadInvestigator.name;
          this.leadInvestigator.organisation =
            resp.data.leadInvestigator.organisation;
          this.leadInvestigator.fte = resp.data.leadInvestigator.fte;
          console.log(this.categories);
        });
    },
  },
  mounted() {
    if (this.$store.state.projectIndex == -1) {
      this.$router.push("/");
    }
    window.scrollTo(0, 0);
    this.load_clearance_form();
  },
};
</script>
<style>
/* td,
th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
} */
#b {
  border: 1px solid #dddddd;
  text-align: left;
  font-size: small;
  /* padding: 8px; */
  padding-left: 8px;
  padding-right: 8px;
  padding-top: 4px;
  padding-bottom: 4px;
  white-space: pre-line; /* Preserves enters */
}
#be {
  border-top: 1px solid #dddddd;
  border-left: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
  text-align: left;
  font-size: small;
  /* padding: 8px; */
  padding-left: 8px;
  padding-right: 8px;
  padding-top: 4px;
  padding-bottom: 4px;
  white-space: pre-line; /* Preserves enters */
}
#bnt {
  border-right: 1px solid #dddddd;
  border-left: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
  text-align: left;
  font-size: small;
  /* padding: 8px; */
  padding-left: 8px;
  padding-right: 8px;
  padding-top: 4px;
  padding-bottom: 4px;
  white-space: pre-line; /* Preserves enters */
}
#bnte {
  border-left: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
  text-align: left;
  font-size: small;
  /* padding: 8px; */
  padding-left: 8px;
  padding-right: 8px;
  padding-top: 4px;
  padding-bottom: 4px;
  white-space: pre-line; /* Preserves enters */
}
#form-box {
  padding: 0px;
  margin: 0px;
  margin-top: 5px;
}
#no-margin-x {
  padding-left: 0px;
  padding-right: 0px;
  /* margin-left: 0px;
  margin-right: 0px; */
}
#no-spacing-a {
  padding: 0px;
  margin: 0px;
}
#bg-darkblue {
  border: 1px solid darkblue;
}
th.bg-blue {
  background: RGB(191, 253, 252);
}
td.bg-blue {
  background: RGB(191, 253, 252);
}
table {
  border-collapse: collapse;
  /* border: 2px solid #000000; */
  width: 100%;
}
table.border-middle {
  border-left: 3px solid black;
  border-right: 3px solid black;
  border-bottom: 3px solid black;
}
table.border-bottom {
  border-left: 3px solid black;
  border-right: 3px solid black;
  border-bottom: 3px solid black;
}
table.border-all {
  border: 3px solid black;
}
</style>
