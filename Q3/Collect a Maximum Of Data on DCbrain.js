const request = require('request');
const cheerio = require('cheerio');
const fs = require('fs');

// Define the URLs to request
const urls = [
  'https://www.dcbrain.com/about-us',
  'https://www.dcbrain.com/solutions',
  'https://www.dcbrain.com/blog'
];

// Define the languages to collect data in
const languages = ['en', 'fr', 'es'];

// Loop through each URL and language to collect data
for (let url of urls) {
  for (let lang of languages) {
    // Make a request to the URL with the language setting
    const options = {
      url: `${url}?lang=${lang}`,
      headers: {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'
      }
    };
    request(options, (error, response, html) => {
      if (!error && response.statusCode == 200) {
        // Load the HTML into Cheerio
        const $ = cheerio.load(html);
        
        // Collect data from the page
        const data = $('p').text(); // Collect all text in <p> tags
        
        // Save the data to a file
        fs.writeFile(`data_${lang}.txt`, data, (err) => {
          if (err) throw err;
          console.log(`Data saved to data_${lang}.txt`);
        });
      } else {
        console.log(`Error requesting ${options.url}: ${error}`);
      }
    });
  }
}
