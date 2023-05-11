<template>

  <Line :data="data" :options="options" />

</template>

<script setup>

    import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js'
    import { Line } from 'vue-chartjs'
    import { defineProps } from 'vue';

    ChartJS.register( CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend )

    const props = defineProps({
        timePeriod: Array,
        bloodSugar: Array,
        insulinDosis: Array
    });

    const data = {
        labels: props.timePeriod,
        datasets: [
            {
                label: 'Blutzucker',
                backgroundColor: '#f87979',
                data: props.bloodSugar
            },
            {
                label: 'Insulin Dosis',
                backgroundColor: '#90f779',
                data: props.insulinDosis
            }
        ]
    }

    const options = { responsive: true }

</script>

