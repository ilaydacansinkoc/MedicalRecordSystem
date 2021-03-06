# Medical Record System

The project: A medical record system with role‐based access control

Description:
 The project will involve a database of medical records accessible by doctor(s),
patient, nurse(s), and relatives.

 The records shall be created by the doctors (fictitious records, all 256 characters long,
including the name (first and last), birth date and the diagnostics for the patient)

 The data base will have a password‐controlled access control system
    
o All doctors will have access using user defined user‐name and password, of
course the administration should verify that they are really doctors.

o The patients will be issued a username as first letter of name + last‐name,
initial authorization will be issued by the doctor. A scheme should be devised
for conflicting user names. The password should be randomly generated by
the system.

o Nurses will be issued passwords similar to the patients.

o The relatives will be defined to the system by the patients.

o The passwords should be stored using SHA‐256 hashing.

 Accessing the records will be as follows:
    o Doctors will have access to all the records of all patients, but authorization
will be given by the patients.
    o Patients will only access to their own records.
    o Relatives may only access to records defined by the patients.
    o Nurses will be authorized by the doctors, they can only access to records for
      which the doctor has the authority.
      
 The records will be transmitted between doctors’ offices using symmetric key
cryptography of your choice.

 The software should have a different user interface for users and admin.
