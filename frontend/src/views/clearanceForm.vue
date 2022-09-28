<template>
    <v-app>
        <v-container>
            <v-row class="pa-5">
                <h2 class="ml-auto mr-auto">Research Funding Clearance Form</h2>
            </v-row>

            <v-row class="px-5 my-5" width="100%">
                <v-card outlined elevation="5" width="100%" max-width="970px" class="mx-auto">
                    <v-card-title class="mx-auto">HERDC research requirements</v-card-title>
                    <v-card-text>Does the project meet the <a
                            href="https://www.utas.edu.au/research-admin/research-funding/finding-and-applying-for-funding/definition-of-research">five
                            core criteria</a>? Please explain how:</v-card-text>
                    <v-card-actions class="px-3">
                        <v-textarea outlined width="100%"></v-textarea>
                    </v-card-actions>
                </v-card>
            </v-row>

            <v-row class="px-5 my-5">
                <v-card outlined elevation="5" max-width="970px" class="mx-auto">
                    <v-row class="px-5 pt-5 ma-5">
                        <v-row width="100%">
                            <v-col cols="6">
                                <v-menu ref="menu" v-model="menu" :close-on-content-click="false"
                                    :return-value.sync="date" transition="scale-transition" offset-y min-width="auto">
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-text-field v-model="date" label="Proposed commencement date"
                                            prepend-icon="mdi-calendar" readonly v-bind="attrs" v-on="on">
                                        </v-text-field>
                                    </template>
                                    <v-date-picker v-model="date" no-title scrollable>
                                        <v-spacer></v-spacer>
                                        <v-btn text color="primary" @click="menu = false">
                                            Cancel
                                        </v-btn>
                                        <v-btn text color="primary" @click="$refs.menu.save(date)">
                                            OK
                                        </v-btn>
                                    </v-date-picker>
                                </v-menu>
                            </v-col>
                            <v-col cols="6">
                                <v-menu ref="menu2" v-model="menu2" :close-on-content-click="false"
                                    :return-value.sync="date2" transition="scale-transition" offset-y>
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-text-field v-model="date2" label="Proposed completion date"
                                            prepend-icon="mdi-calendar" readonly v-bind="attrs" v-on="on">
                                        </v-text-field>
                                    </template>
                                    <v-date-picker v-model="date2" no-title scrollable>
                                        <v-spacer></v-spacer>
                                        <v-btn text color="primary" @click="menu2 = false">
                                            Cancel
                                        </v-btn>
                                        <v-btn text color="primary" @click="$refs.menu2.save(date2)">
                                            OK
                                        </v-btn>
                                    </v-date-picker>
                                </v-menu>
                            </v-col>
                        </v-row>

                        <v-row>
                            <v-col cols="6">
                                <v-text-field label="Funding Body / Client" outlined="true"></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field label="Scheme" outlined="true"></v-text-field>
                            </v-col>
                        </v-row>
                    </v-row>
                    <v-divider></v-divider>
                    <v-row class="ma-5 pt-0">
                        <v-card-text class="mt-0 py-0">
                            For contract research and consultancies only, provide a contact
                            name and email address:
                        </v-card-text>

                        <v-col cols="6">
                            <v-text-field label="Contact Name"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            <v-text-field label="Email Address" type="email"></v-text-field>
                        </v-col>
                    </v-row>
                </v-card>
            </v-row>

            <v-row class="px-5 my-5">
                <v-card outlined elevation="5" width="100%" class="pa-0 mx-auto" max-width="970px">
                    <v-card-title>Lead Investigator - UTAS or external</v-card-title>
                    <v-card-actions>
                        <v-col cols="4">
                            <v-text-field label="Name"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            <v-text-field label="UTAS discipline/unit or External Organisation"></v-text-field>
                        </v-col>
                        <v-col cols="2">
                            <v-text-field label="FTE %"></v-text-field>
                        </v-col>
                    </v-card-actions>
                    <v-divider></v-divider>

                    <v-card-title class="pb-2">All Other Investigators (incl. students)</v-card-title>
                    <v-card-actions v-for="(item, i) in 3">
                        <v-col cols="4" class="py-0">
                            <v-text-field label="Name" class="py-0"></v-text-field>
                        </v-col>
                        <v-col cols="6" class="py-0">
                            <v-text-field label="UTAS discipline/unit or External Organisation" class="py-0">
                            </v-text-field>
                        </v-col>
                        <v-col cols="2" class="py-0">
                            <v-text-field label="FTE %" class="py-0"></v-text-field>
                        </v-col>
                    </v-card-actions>
                    <v-divider></v-divider>

                    <v-row>
                        <v-col cols="7">
                            <v-card-title class="pb-0">Higher Degree Research students</v-card-title>
                        </v-col>
                        <v-col class="mt-5 mr-5" style="text-align: right">
                            <i style="color: grey">A <a href="">Deed of Assignment</a> may be required</i>
                        </v-col>
                    </v-row>
                    <v-card-actions>
                        <v-col cols="6">
                            <v-text-field label="Name" class="py-0"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            <v-text-field label="UTAS discipline/unit or External Organisation" class="py-0">
                            </v-text-field>
                        </v-col>
                    </v-card-actions>
                    <v-card-actions class="py-0">
                        <v-col cols="3" class="py-0">
                            <v-subheader>Type of involvement:</v-subheader>
                        </v-col>
                        <v-col cols="3" class="py-0">
                            <v-checkbox v-model="checkbox" label="Associated with project / unpaid" class="py-0">
                            </v-checkbox>
                        </v-col>
                        <v-col cols="3" class="py-0">
                            <v-checkbox v-model="checkbox" label="Paid from project - casual employee" class="py-0">
                            </v-checkbox>
                        </v-col>
                        <v-col cols="3" class="py-0">
                            <v-checkbox v-model="checkbox" label="Scholarship paid from project" class="py-0">
                            </v-checkbox>
                        </v-col>
                    </v-card-actions>
                </v-card>
            </v-row>

            <v-row class="px-5 my-5">
                <v-card outlined elevation="5" width="100%" class="pa-0 mx-auto" max-width="970px">
                    <v-card-title>Research Codes</v-card-title>
                    <v-row class="ma-5">
                        <v-col cols="4" style="border-right:1px solid grey" class="">
                            <v-row>
                                <v-col cols="9">
                                    <v-row>
                                        <p>Field of Research*</p>
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
        </v-container>
    </v-app>
</template>
