function reverse_words(text) {
	var arrWords = text.split(' ').filter(Boolean);
	var arrReversedWords = [];
	
	for(var i = arrWords.length - 1; i >= 0; i--) {
		arrReversedWords.push(arrWords[i]);
	}
	
	return arrReversedWords.join(' ');
}