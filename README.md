# cardsPreDefHashPartitioner
Problem statement : Counting number of cards present in the form of text file. Categorize it per type of suit.

		1. Counting number of cards present in the form of text file. Categorize it per type of suit.
		2. Input : Large Desk of cards : Data present in Git.
		3. Code Git link : 
			a. Contains 2 class files for driver & mapper. And the predefined reducer LongSumReducer is benig used.
			b. Mapper maps the key depending on suit type. So suit type is read from the input file.
			c. Predefined reducer being used which sums the mapper output.
			d. Predefined hash partitioner is used to partition the suites per its categoreis. This is being called during the end of mapper class & before reducer class.
