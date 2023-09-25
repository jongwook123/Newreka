import React, { useEffect, useRef } from 'react';
import * as d3 from 'd3';
import cloud from 'd3-cloud';

const dummydata = {
  '1': 1000,
  '2': 2000,
  '3': 3000,
  '4': 4000,
  '5': 5000,
  '6': 6000,
  '7': 7000,
  '8': 8000,
  '9': 9000,
  '10': 10000,
  
};

function handleCircleClick(event, d) {
  console.log('Circle clicked:', d.text);
  // Add your custom event handling logic here
}

function WordCloudPage() {
    const wordRef = useRef(null);

    useEffect(() => {
        const words = Object.entries(dummydata)
            .sort(([, a], [, b]) => b - a)
            .map(([text, size]) => ({ text: text, size: size }));

        const layout = cloud()
            .size([800,700])
            .words(words)
            .rotate(() => ~~(Math.random() *2) )
            .fontSize(d => Math.sqrt(d.size))
            .on('end', draw);

        layout.start();

        function draw(words) {
          const top5Words = words.slice(0, 5); // Select top 5 words
          let group = d3.select(wordRef.current).append('svg')
                .attr('width', layout.size()[0])
                .attr('height', layout.size()[1])
                .append('g')
                .attr("transform", "translate(" + layout.size()[0] /2 + "," + layout.size()[1] /2 + ")");
        
          // Modify position based on size (z-index based on size)
          words.forEach(d => {
            d.z = Math.sqrt(d.size); // Adjust the function based on your preference
          });
          
          const simulation = d3.forceSimulation(words)
            .force('charge', d3.forceManyBody().strength(50))
            .force('collide', d3.forceCollide().radius(d => Math.max(10, Math.abs(d.size*0.95)))) // Adjust the additional radius
            .stop();

          for (let i = 0; i < 70; ++i) simulation.tick();
          group.selectAll('.word-text')
            .data(words)
            .enter()
            .append("text")
            .style('font-size', d => `${d.size}px`)
            .style('fill', 'black')
            .attr('text-anchor', 'middle')
            .attr("class", "word-text")
            .attr("transform", d => `translate(${d.x},${d.y + Math.max(10, Math.abs(d.size * 0.8)) / 2})`)  // Adjust the y-position to center the text vertically within the circle
            .attr('z', d => d.z)
            .text(d => d.text);

          // Append circles after text, so they visually appear on top
          group.selectAll('.word-circle')
            .data(words)
            .enter()
            .append('circle')
            .attr('r', d => Math.max(10, Math.abs(d.size)))
            .attr('class', 'word-circle')
            .attr('transform', d => `translate(${d.x},${d.y})`)
            .attr('fill', d => top5Words.includes(d) ? 'blue' : 'gray')
            .attr('stroke', 'black')
            .attr('stroke-width', 1)
            .attr('opacity', 0.7)
            .attr('z', d => d.z)
            .on('click', handleCircleClick);
        }

    }, []);

    return <div ref={wordRef} />;
}

export default WordCloudPage;
