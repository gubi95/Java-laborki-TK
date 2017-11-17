function count_words(text) {
	var arrWords = text.split(' ').filter(Boolean);
	var dictWordsToQuantity = { };
	
	for(var i = 0; i < arrWords.length; i++) {
		if(dictWordsToQuantity[arrWords[i]] == null) {
			dictWordsToQuantity[arrWords[i]] = 1;
		}
		else {		
			dictWordsToQuantity[arrWords[i]]++;
		}
	}
	
	var strRetValue = '';
	for(var key in dictWordsToQuantity) {
		if (dictWordsToQuantity.hasOwnProperty(key)) {
			strRetValue += (key + ' - ' + dictWordsToQuantity[key] + ', ');  
		}
	}
	
	return strRetValue.length > 1 ? strRetValue.substring(0, strRetValue.length - 2) : '';
}