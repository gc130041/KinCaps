document.addEventListener('DOMContentLoaded', function() {
    
    const slider1 = document.getElementById('slider-1');
    const slider2 = document.getElementById('slider-2');
    const range1Value = document.getElementById('range1-value');
    const range2Value = document.getElementById('range2-value');
    const sliderTrack = document.querySelector('.slider-track');
    const filterForm = document.getElementById('filter-form');
    const productItems = document.querySelectorAll('.product-item');

    function updatePriceSlider() {
        let val1 = parseInt(slider1.value);
        let val2 = parseInt(slider2.value);

        if (val1 > val2) {
            [val1, val2] = [val2, val1];
            slider1.value = val1;
            slider2.value = val2;
        }

        range1Value.textContent = `Q${val1}`;
        range2Value.textContent = `Q${val2}`;
        
        const sliderMax = slider1.max;
        const percent1 = (val1 / sliderMax) * 100;
        const percent2 = (val2 / sliderMax) * 100;
        sliderTrack.style.background = `linear-gradient(to right, #ddd ${percent1}%, #007bff ${percent1}%, #007bff ${percent2}%, #ddd ${percent2}%)`;
    }

    function filterProducts() {
        const minPrice = parseInt(slider1.value);
        const maxPrice = parseInt(slider2.value);
        
        const selectedBrands = Array.from(document.querySelectorAll('input[id^="brand"]:checked')).map(cb => cb.value);
        const selectedCategories = Array.from(document.querySelectorAll('input[id^="cat"]:checked')).map(cb => cb.value);

        productItems.forEach(item => {
            const itemPrice = parseInt(item.dataset.price);
            const itemBrand = item.dataset.brand;
            const itemCategory = item.dataset.category;

            const priceMatch = itemPrice >= minPrice && itemPrice <= maxPrice;
            const brandMatch = selectedBrands.length === 0 || selectedBrands.includes(itemBrand);
            const categoryMatch = selectedCategories.length === 0 || selectedCategories.includes(itemCategory);

            if (priceMatch && brandMatch && categoryMatch) {
                item.style.display = '';
            } else {
                item.style.display = 'none';
            }
        });
    }

    if (filterForm) {
        filterForm.addEventListener('input', function(event) {
            if (event.target.type === 'range') {
                 updatePriceSlider();
            }
            filterProducts();
        });
    }
    
    updatePriceSlider();
});