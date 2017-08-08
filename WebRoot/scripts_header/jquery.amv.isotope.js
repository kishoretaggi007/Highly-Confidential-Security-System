$(function(){

	var data = {
		statuses: 'private public ma'.split(' '),
		sectors: 'customer enterprise healthcare'.split(' '),
		industries: 'big-data cloud communication devices digital media gaming global hardware hst mobile personalized medicine semiconductor therapeutics'.split(' ')
	}

	var status, sector, industry;
	var items = ''

	for (var i=0, len1 = data.statuses.length; i < len1; i++) {
		status = data.statuses[i];
		for (var j=0, len2 = data.sectors.length; j < len2; j++) {
			sector = data.sectors[j];
			for (var k=0, len3 = data.industries.length; k < len3; k++) {
				industry = data.industries[k];
			}
		}
	}

	var $container = $('.portfolio #content');
	var $filterDisplay = $('#filter-display');
	$container.append( items );
	$container.isotope()

	var filters = {};

	$('#side input[type=checkbox].all').hide();
	$('#side').on( 'change', function( jQEvent ) {
		var checkbox = jQEvent.target;
		var $checkbox = $( checkbox );
		var value = checkbox.value;
		var isAll = $checkbox.hasClass('all');

		var group = $checkbox.parents('#side ul').attr('id');
		if ( !filters[ group ] ) {
			filters[ group ] = [];
		}

		var index = $.inArray( checkbox.value, filters[ group ] );

		if ( isAll ) {
			delete filters[ group ];
			if ( !checkbox.checked ) {
				checkbox.checked = 'checked';
			}
		}

		if ( checkbox.checked ) {
			var selector = isAll ? 'input' : 'input.all';
			$checkbox.siblings( selector ).removeAttr('checked');
			if ( !isAll && index === -1 ) {
				filters[ group ].push( checkbox.value );
			}
		} else if ( !isAll ) {
			filters[ group ].splice( index, 1 );
			if ( !$checkbox.siblings('[checked]').length ) {
				$checkbox.siblings('input.all').attr('checked', 'checked');
			}
		}

		var i = 0;
		var comboFilters = [];
		var message = []

		for ( var prop in filters ) {
			message.push( filters[ prop ].join(' ') );
			var filterGroup = filters[ prop ];
			if ( !filterGroup.length ) {
				continue;
			}

			if ( i === 0 ) {
				comboFilters = filterGroup.slice(0);
			} else {
				var filterSelectors = [];
				var groupCombo = comboFilters.slice(0); // [ A, B ]
				for (var k=0, len3 = filterGroup.length; k < len3; k++) {
					for (var j=0, len2 = groupCombo.length; j < len2; j++) {
						filterSelectors.push( groupCombo[j] + filterGroup[k] ); // [ 1, 2 ]
					}
				}
				comboFilters = filterSelectors
			}
			i++
		}
		$container.isotope({ filter: comboFilters.join(', ') });
		$filterDisplay.text( comboFilters.join(', ') );
	});

});
