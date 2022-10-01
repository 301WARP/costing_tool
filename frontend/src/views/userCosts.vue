<template>
    <v-app>
        <v-container>
            <v-row class="pa-15">
                <h2 class="mx-auto">Direct Non-Labour Costs</h2>
            </v-row>
        </v-container>
    </v-app>
</template>
<script>
const axios = require("axios").default;
import router from "../router";

export default {
    mounted() {
        if (this.$store.state.projectIndex == -1) {
            this.$router.push("/");
        }
    },
};
</script>