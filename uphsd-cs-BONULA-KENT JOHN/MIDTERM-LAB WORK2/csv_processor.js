/**
 * =====================================================
 * Student Name    : BOÑULA, KENT JOHN C.
 * Course          : BSCSIT 9338 Programming 2
 * Assigned MPs    : MP02, MP11, MP18
 * Runtime         : Node.js
 * =====================================================
 */

const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Prompt user for dataset location
rl.question('Enter the full path of the CSV dataset: ', (filePath) => {
    fs.readFile(filePath, 'utf8', (err, data) => {
        if (err) {
            console.error("Error: Could not read file. Check the path.");
            rl.close();
            return;
        }

        const lines = data.split(/\r?\n/);
        const validRecords = [];
        const frequency = {};

        lines.forEach(line => {
            if (!line.trim()) return;

            // Split by comma
            const columns = line.split(/,(?=(?:(?:[^"]*"){2})*[^"]*$)/);

            // MP18: Detect and remove rows with empty fields
            const hasEmpty = columns.some(col => col.trim() === "");

            if (!hasEmpty && columns.length >= 4) {
                validRecords.push(columns);

                // MP11: Frequency count for Exam column (Index 3)
                const exam = columns[3].replace(/"/g, "").trim();
                frequency[exam] = (frequency[exam] || 0) + 1;
            }
        });

        // MP02: Display first 10 rows
        console.log("\n--- MP02: FIRST 10 VALID ROWS ---");
        validRecords.slice(0, 10).forEach(row => {
            console.log(row.join(" | "));
        });

        // MP11: Display Frequency Count
        console.log("\n--- MP11: EXAM FREQUENCY COUNT ---");
        console.table(frequency);

        console.log("\n--- MP18: Processing complete with empty rows removed. ---");
        rl.close();
    });
});