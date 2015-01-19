
(function(){
	var app = angular.module('store',[]);

	app.controller('StoreController', function(){
		this.products = gems;
	});
	var gems = [
	            {
	            	name:'Dodecahedron',
	            	price: 2.95,
	            	description: 'description Dodecahedron',
	            	canPurchase: true,
	            },
	            {
	            	name:'Pentagonal Gem',
	            	price: 5.95,
	            	description: 'Decription Pentagonal Gem',
	            	canPurchase: false,
	            }
	            ];

	app.controller('PanelController', function(){
		this.tab = 1;

		this.selectTab = function(setTab){
			this.tab =  setTab;
		};
		this.isSelected = function(checkTab){
			return this.tab === checkTab;
		};
	});
});

})();
