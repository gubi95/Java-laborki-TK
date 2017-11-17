function remove_5_length_words(text) {
	var arrWords = text.split(' ').filter(Boolean);
	var arrNewWords = [];	
	
	for(var i = 0; i < arrWords.length; i++) {
		if(arrWords[i].length != 5) {
			arrNewWords.push(arrWords[i]);
		}
	}
	
	return arrNewWords.join(' ');
}