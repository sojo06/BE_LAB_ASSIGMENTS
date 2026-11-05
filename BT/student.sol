
// pragma solidity ^0.8.20;

// contract Student {

//     struct StudentData {
//         uint rollNo;
//         string name;
//         uint age;
//     }

//     StudentData[] students;

//     // Add student
//     function addStudent(uint rollNo, string memory name, uint age) public {
//         students.push(StudentData(rollNo, name, age));
//     }

//     // Display student details
//     function getStudent(uint index) public view returns (uint, string memory, uint) {
//         StudentData memory s = students[index];
//         return (s.rollNo, s.name, s.age);
//     }

//     // Get total students
//     function getCount() public view returns (uint) {
//         return students.length;
//     }
// }
pragma solidity ^0.8.20;

contract Student{
    struct StudentData{
        uint rollno;
        uint age;
        string name;
    }
    StudentData[] students;
    function addStudent(uint r,uint a, string memory n) public {
        students.push(StudentData(r,a,n));
    }
    function getStudent(uint index) public view returns(uint,uint,string memory){
        StudentData memory std = students[index];
        return(std.rollno,std.age,std.name);
    }
    function getCount()public view returns(uint){
        return students.length;
    }

}