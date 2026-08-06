document.addEventListener('DOMContentLoaded', function() {
    const ctx = document.getElementById('statisticsChart').getContext('2d');
    
    // Crear el gráfico
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            datasets: [
                {
                    label: 'Active Users',
                    data: generateData(400, 900),
                    borderColor: 'rgb(99, 179, 237)',
                    backgroundColor: 'rgba(99, 179, 237, 0.3)',
                    fill: true
                },
                {
                    label: 'New Visitors',
                    data: generateData(200, 400),
                    borderColor: 'rgb(251, 189, 35)',
                    backgroundColor: 'rgba(251, 189, 35, 0.3)',
                    fill: true
                },
                {
                    label: 'Subscribers',
                    data: generateData(100, 300),
                    borderColor: 'rgb(252, 129, 129)',
                    backgroundColor: 'rgba(252, 129, 129, 0.3)',
                    fill: true
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom'
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    grid: {
                        drawBorder: false
                    }
                },
                x: {
                    grid: {
                        display: false
                    }
                }
            }
        }
    });
});

// Función para generar datos de ejemplo
function generateData(min, max) {
    return Array.from({length: 12}, () => 
        Math.floor(Math.random() * (max - min + 1)) + min
    );
}