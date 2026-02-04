/* Programmer Identifier: [Your Name] [Your ID] */

// This is your short list, but you can paste your 400 rows here
const csvContent = `073900438,Osbourne,Wakenshaw,69,5,52,12,78
114924014,Albie,Gierardi,58,92,16,57,97
111901632,Eleen,Pentony,43,81,34,36,16
084000084,Arie,Okenden,31,5,14,39,99
272471551,Alica,Muckley,49,66,97,3,95`;

let students = [];

// 1. CONVERT DATA TO LIST
function parseCSV() {
    const rows = csvContent.trim().split('\n');
    students = rows.map(row => {
        const cols = row.split(',');
        return {
            id: cols[0],
            name: `${cols[1]} ${cols[2]}`,
            grade: cols[7] // Taking index 7 as requested
        };
    });
}

// 2. SHOW LIST ON SCREEN
function renderTable(dataToDisplay = students) {
    const tableBody = document.getElementById('tableBody');
    tableBody.innerHTML = ''; 

    dataToDisplay.forEach((student, index) => {
        const row = `
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.grade}</td>
                <td>
                    <button class="btn-delete" onclick="deleteStudent(${index})">Delete</button>
                </td>
            </tr>`;
        tableBody.innerHTML += row;
    });

    document.getElementById('recordCount').innerText = dataToDisplay.length;
}

// 3. SEARCH LIST
function searchTable() {
    const query = document.getElementById('searchInput').value.toLowerCase();
    const filtered = students.filter(s => 
        s.name.toLowerCase().includes(query) || 
        s.id.includes(query)
    );
    renderTable(filtered);
}

// 4. ADD NEW TO LIST
function addStudent() {
    const id = document.getElementById('idIn').value;
    const name = document.getElementById('nameIn').value;
    const grade = document.getElementById('preIn').value;

    if (id && name && grade) {
        students.unshift({ id, name, grade }); 
        renderTable();
        // Clear Inputs
        document.getElementById('idIn').value = '';
        document.getElementById('nameIn').value = '';
        document.getElementById('preIn').value = '';
    }
}

// 5. REMOVE FROM LIST
function deleteStudent(index) {
    if(confirm("Delete this record?")) {
        students.splice(index, 1);
        renderTable();
    }
}

// START APP
parseCSV();
renderTable();