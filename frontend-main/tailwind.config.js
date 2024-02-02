/** @type {import('tailwindcss').Config} */
export default {
    content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
    theme: {
        screens:{
            'sm':  {'max': '500px'},
            'md': {'max': '1000px'},
            'lg':  {'min':'1001px'},
        },
        extend: {
            backgroundImage: {
                'card': 'linear-gradient(180deg, rgba(38,37,37,1) 0%, rgba(54,53,53,0.95) 100%)'
            },
            backgroundColor: {
                'primary': 'rgba(217,217,217,0.13)',
                'secondary':'rgba(102,102,102,0.54)'
            },
            textColor: {
                'primary': '#C2A022'
            },
            colors:{
                'primary': '#C2A022'
            }
        },
    },
    plugins: [],
}
