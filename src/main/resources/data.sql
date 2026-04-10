-- Sample data for MediConnect Database
-- Password for all users: Admin@123 (BCrypt hashed)

-- Admin Users
INSERT INTO users (full_name, email, password, role, phone, is_active) VALUES
('System Admin', 'admin@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'ADMIN', '9999999999', true),
('Hospital Manager', 'manager@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'ADMIN', '9888888888', true);

-- Doctor Users and Profiles
INSERT INTO users (full_name, email, password, role, phone, is_active) VALUES
('Dr. Sarah Johnson', 'sarah.johnson@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'DOCTOR', '9876543210', true),
('Dr. Michael Chen', 'michael.chen@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'DOCTOR', '9876543211', true),
('Dr. Emily Davis', 'emily.davis@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'DOCTOR', '9876543212', true),
('Dr. Robert Wilson', 'robert.wilson@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'DOCTOR', '9876543213', true),
('Dr. Lisa Anderson', 'lisa.anderson@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'DOCTOR', '9876543214', true);

INSERT INTO doctors (user_id, specialization, license_number, experience_years, consultation_fee, bio, available_days, available_time_start, available_time_end, rating) VALUES
(3, 'Cardiology', 'DOC-2024-001', 12, 800.00, 'Board-certified cardiologist specializing in heart diseases, echocardiography, and preventive cardiology. Over 12 years of experience in treating complex cardiac conditions.', 'MON,TUE,WED,THU,FRI', '09:00:00', '17:00:00', 4.8),
(4, 'Neurology', 'DOC-2024-002', 15, 900.00, 'Neurologist with expertise in stroke management, epilepsy, and neurodegenerative disorders. Published researcher in neurological sciences.', 'MON,WED,FRI,SAT', '10:00:00', '18:00:00', 4.9),
(5, 'Dermatology', 'DOC-2024-003', 8, 600.00, 'Dermatologist specializing in cosmetic dermatology, skin cancer screening, and acne treatment. Member of American Academy of Dermatology.', 'TUE,THU,FRI,SAT', '09:30:00', '16:30:00', 4.7),
(6, 'Orthopedics', 'DOC-2024-004', 20, 1000.00, 'Orthopedic surgeon with expertise in joint replacement, sports injuries, and minimally invasive procedures. Former team physician for professional athletes.', 'MON,TUE,WED,THU', '08:00:00', '16:00:00', 4.9),
(7, 'Pediatrics', 'DOC-2024-005', 10, 500.00, 'Pediatrician dedicated to child healthcare, vaccinations, and developmental pediatrics. Special interest in childhood obesity and nutrition.', 'MON,TUE,WED,FRI,SAT', '08:30:00', '15:30:00', 4.8);

-- Patient Users and Profiles
INSERT INTO users (full_name, email, password, role, phone, is_active) VALUES
('John Smith', 'john.smith@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9123456789', true),
('Maria Garcia', 'maria.garcia@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9234567890', true),
('David Brown', 'david.brown@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9345678901', true),
('Jennifer Lee', 'jennifer.lee@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9456789012', true),
('Robert Taylor', 'robert.taylor@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9567890123', true),
('Anna Martinez', 'anna.martinez@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9678901234', true),
('James Wilson', 'james.wilson@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9789012345', true),
('Linda Johnson', 'linda.johnson@email.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PATIENT', '9890123456', true);

INSERT INTO patients (user_id, date_of_birth, gender, blood_group, allergies, emergency_contact, address) VALUES
(8, '1985-03-15', 'MALE', 'O+', 'Penicillin, Shellfish', 'Jane Smith - 9876543210', '123 Main Street, Downtown, City 12345'),
(9, '1992-07-22', 'FEMALE', 'A-', 'None', 'Carlos Garcia - 9876543211', '456 Oak Avenue, Suburb, City 12346'),
(10, '1978-11-08', 'MALE', 'B+', 'Sulfa drugs', 'Mary Brown - 9876543212', '789 Pine Road, Rural Area, City 12347'),
(11, '1988-05-30', 'FEMALE', 'AB+', 'Latex', 'Tom Lee - 9876543213', '321 Elm Street, Urban Area, City 12348'),
(12, '1995-09-12', 'MALE', 'O-', 'None', 'Sarah Taylor - 9876543214', '654 Maple Drive, Residential, City 12349'),
(13, '1980-01-25', 'FEMALE', 'A+', 'Aspirin', 'Miguel Martinez - 9876543215', '987 Cedar Lane, Suburban, City 12350'),
(14, '1975-12-03', 'MALE', 'B-', 'Codeine', 'Patricia Wilson - 9876543216', '147 Birch Boulevard, Downtown, City 12351'),
(15, '1990-06-18', 'FEMALE', 'AB-', 'None', 'Mark Johnson - 9876543217', '258 Spruce Street, Commercial, City 12352');

-- Pharmacist Users and Profiles
INSERT INTO users (full_name, email, password, role, phone, is_active) VALUES
('Mike Thompson', 'mike.thompson@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PHARMACIST', '9234567890', true),
('Rachel Green', 'rachel.green@mediconnect.com', '$2b$12$caaiMaogPUo3orS2qq5k6umpytxZhFlv9Ic8rXlYeZPmtO3ON6IhW', 'PHARMACIST', '9345678901', true);

INSERT INTO pharmacists (user_id, license_number, pharmacy_name, pharmacy_address) VALUES
(16, 'PHARM-2024-001', 'MediConnect Central Pharmacy', '123 Health Street, Medical District, City 12345'),
(17, 'PHARM-2024-002', 'MediConnect Express Pharmacy', '456 Wellness Boulevard, Shopping Center, City 12346');

-- Appointments
INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status, type, symptoms, notes, meeting_link) VALUES
(1, 1, '2024-12-20', '10:00:00', 'COMPLETED', 'VIRTUAL', 'Chest pain, shortness of breath', 'Patient reported chest pain after exercise. ECG normal, prescribed beta blockers.', 'https://meet.mediconnect.com/appointment/1'),
(2, 2, '2024-12-21', '14:30:00', 'CONFIRMED', 'VIRTUAL', 'Frequent headaches, dizziness', 'Neurological examination scheduled. MRI recommended.', 'https://meet.mediconnect.com/appointment/2'),
(3, 3, '2024-12-22', '11:15:00', 'PENDING', 'IN_PERSON', 'Skin rash, itching', 'Allergic reaction suspected. Skin biopsy recommended.', NULL),
(4, 4, '2024-12-23', '09:00:00', 'COMPLETED', 'VIRTUAL', 'Knee pain, limited mobility', 'X-ray shows arthritis. Physical therapy recommended.', 'https://meet.mediconnect.com/appointment/3'),
(5, 5, '2024-12-24', '13:45:00', 'CONFIRMED', 'VIRTUAL', 'Child has fever, cough', 'Viral infection diagnosed. Rest and fluids prescribed.', 'https://meet.mediconnect.com/appointment/4'),
(6, 1, '2024-12-25', '15:30:00', 'PENDING', 'VIRTUAL', 'High blood pressure', 'Monitoring required. Lifestyle changes recommended.', NULL),
(7, 2, '2024-12-26', '10:30:00', 'COMPLETED', 'IN_PERSON', 'Memory loss, confusion', 'Cognitive assessment completed. Follow-up in 3 months.', NULL),
(8, 3, '2024-12-27', '16:00:00', 'CONFIRMED', 'VIRTUAL', 'Acne breakouts', 'Prescription for topical treatment. Skincare routine advised.', 'https://meet.mediconnect.com/appointment/5');

-- Medical Records
INSERT INTO medical_records (patient_id, doctor_id, appointment_id, diagnosis, treatment, notes, record_date) VALUES
(1, 1, 1, 'Stable Angina', 'Beta blockers, Lifestyle modifications', 'Patient shows good response to treatment. Regular follow-up recommended.', '2024-12-20'),
(2, 2, 2, 'Migraine with Aura', 'Triptans, Preventive medication', 'Patient educated about trigger avoidance. Stress management techniques discussed.', '2024-12-21'),
(3, 3, 3, 'Contact Dermatitis', 'Topical corticosteroids, Antihistamines', 'Patch testing recommended to identify allergens. Avoid known triggers.', '2024-12-22'),
(4, 4, 4, 'Osteoarthritis', 'NSAIDs, Physical therapy', 'Patient referred to orthopedic specialist. Weight management plan initiated.', '2024-12-23'),
(5, 5, 5, 'Upper Respiratory Infection', 'Antiviral medication, Rest', 'Vaccination status updated. Hygiene practices reinforced.', '2024-12-24'),
(6, 1, 6, 'Hypertension Stage 1', 'ACE inhibitors, Dietary changes', 'Blood pressure monitoring at home. Regular exercise prescribed.', '2024-12-25'),
(7, 2, 7, 'Mild Cognitive Impairment', 'Cognitive exercises, Vitamin supplements', 'Family counseling recommended. Support group suggested.', '2024-12-26'),
(8, 3, 8, 'Acne Vulgaris', 'Topical retinoids, Antibiotics', 'Skincare regimen established. Follow-up in 8 weeks.', '2024-12-27');

-- Prescriptions
INSERT INTO prescriptions (appointment_id, patient_id, doctor_id, diagnosis, instructions, status, issued_date, valid_until) VALUES
(1, 1, 1, 'Stable Angina', 'Take medications as prescribed. Follow up in 3 months.', 'DISPENSED', '2024-12-20', '2025-03-20'),
(2, 2, 2, 'Migraine with Aura', 'Take as needed for acute attacks. Avoid triggers.', 'PENDING', '2024-12-21', '2025-03-21'),
(3, 3, 3, 'Contact Dermatitis', 'Apply cream twice daily. Avoid irritants.', 'DISPENSED', '2024-12-22', '2025-01-22'),
(4, 4, 4, 'Osteoarthritis', 'Take with food. Physical therapy 3x/week.', 'PENDING', '2024-12-23', '2025-03-23'),
(5, 5, 5, 'Upper Respiratory Infection', 'Complete full course. Rest and hydrate.', 'DISPENSED', '2024-12-24', '2025-01-24'),
(6, 6, 1, 'Hypertension Stage 1', 'Monitor blood pressure daily. Low salt diet.', 'PENDING', '2024-12-25', '2025-03-25'),
(7, 7, 2, 'Mild Cognitive Impairment', 'Daily cognitive exercises. Family support.', 'DISPENSED', '2024-12-26', '2025-03-26'),
(8, 8, 3, 'Acne Vulgaris', 'Apply at night. Use sunscreen. Follow-up visit.', 'PENDING', '2024-12-27', '2025-03-27');

-- Prescription Medications
INSERT INTO prescription_medications (prescription_id, medication_name, dosage, frequency, duration, quantity, instructions) VALUES
(1, 'Metoprolol', '50mg', 'Once daily', '90 days', 90, 'Take with food in the morning'),
(1, 'Aspirin', '81mg', 'Once daily', '90 days', 90, 'Take with water'),
(2, 'Sumatriptan', '100mg', 'As needed', '60 days', 12, 'Take at onset of migraine, max 2 per day'),
(2, 'Propranolol', '40mg', 'Twice daily', '90 days', 180, 'Take with meals'),
(3, 'Hydrocortisone Cream', '1%', 'Twice daily', '14 days', 1, 'Apply thin layer to affected areas'),
(3, 'Cetirizine', '10mg', 'Once daily', '30 days', 30, 'Take in the evening'),
(4, 'Ibuprofen', '600mg', 'Three times daily', '30 days', 90, 'Take with food, as needed for pain'),
(4, 'Glucosamine', '1500mg', 'Once daily', '90 days', 90, 'Take with largest meal'),
(5, 'Amoxicillin', '500mg', 'Three times daily', '10 days', 30, 'Take with food, complete full course'),
(5, 'Acetaminophen', '500mg', 'Every 6 hours', '5 days', 20, 'Take as needed for fever/pain'),
(6, 'Lisinopril', '10mg', 'Once daily', '90 days', 90, 'Take in the morning'),
(6, 'Hydrochlorothiazide', '12.5mg', 'Once daily', '90 days', 90, 'Take in the morning'),
(7, 'Donepezil', '5mg', 'Once daily', '90 days', 90, 'Take in the evening'),
(7, 'Vitamin D3', '2000 IU', 'Once daily', '90 days', 90, 'Take with meals'),
(8, 'Tretinoin', '0.05%', 'Once daily', '60 days', 1, 'Apply at night, use sunscreen'),
(8, 'Clindamycin', '1%', 'Twice daily', '30 days', 1, 'Apply to affected areas');

-- Lab Reports
INSERT INTO lab_reports (patient_id, doctor_id, report_name, report_type, result_summary, report_date, is_normal) VALUES
(1, 1, 'Lipid Profile', 'BLOOD_TEST', 'Total Cholesterol: 220 mg/dL, HDL: 45 mg/dL, LDL: 140 mg/dL, Triglycerides: 150 mg/dL - Elevated LDL cholesterol', '2024-12-15', false),
(2, 2, 'MRI Brain', 'IMAGING', 'No acute abnormalities. Mild age-related changes.', '2024-12-18', true),
(3, 3, 'Patch Testing', 'ALLERGY_TEST', 'Positive for Nickel, Fragrance Mix - Multiple contact allergies identified', '2024-12-19', false),
(4, 4, 'X-Ray Knee', 'IMAGING', 'Joint space narrowing, osteophyte formation - Moderate osteoarthritis', '2024-12-20', false),
(5, 5, 'CBC with Differential', 'BLOOD_TEST', 'WBC: 12.5 K/uL, Neutrophils: 75%, Lymphocytes: 20% - Leukocytosis consistent with infection', '2024-12-21', false),
(6, 1, 'Blood Pressure Monitoring', 'VITAL_SIGNS', 'Average: 145/92 mmHg - Stage 1 hypertension confirmed', '2024-12-22', false),
(7, 2, 'MMSE Score', 'COGNITIVE_TEST', 'Score: 26/30 - Mild cognitive impairment', '2024-12-23', false),
(8, 3, 'Skin Culture', 'MICROBIOLOGY', 'Negative for bacterial pathogens - No bacterial infection', '2024-12-24', true);

-- Medication Orders
INSERT INTO medication_orders (prescription_id, pharmacist_id, status, total_amount, delivery_address, notes) VALUES
(1, 1, 'READY', 45.50, '123 Main Street, Downtown, City 12345', 'Filled successfully. Patient educated on medication.'),
(2, 2, 'PENDING', 120.00, '456 Oak Avenue, Suburb, City 12346', 'Awaiting insurance approval.'),
(3, 1, 'DELIVERED', 25.00, '789 Pine Road, Rural Area, City 12347', 'Over-the-counter alternative discussed.'),
(5, 2, 'READY', 15.00, '654 Maple Drive, Residential, City 12349', 'Patient allergic to penicillin - alternative prescribed.'),
(7, 1, 'PROCESSING', 85.00, '147 Birch Boulevard, Downtown, City 12351', 'Special authorization required and obtained.'),
(8, 2, 'PENDING', 65.00, '258 Spruce Street, Commercial, City 12352', 'Prescription verification in progress.');

-- Notifications
INSERT INTO notifications (user_id, title, message, type, is_read, created_at) VALUES
(1, 'Appointment Reminder', 'Your appointment with Dr. Sarah Johnson is tomorrow at 10:00 AM', 'APPOINTMENT', false, '2024-12-19 08:00:00'),
(2, 'Lab Results Available', 'Your lipid profile results are now available in your medical records', 'LAB_REPORT', false, '2024-12-15 14:30:00'),
(3, 'Prescription Ready', 'Your prescription is ready for pickup at MediConnect Central Pharmacy', 'PRESCRIPTION', false, '2024-12-22 16:45:00'),
(4, 'Appointment Confirmed', 'Your appointment with Dr. Robert Wilson has been confirmed for Dec 23', 'APPOINTMENT', true, '2024-12-18 10:15:00'),
(5, 'Vaccination Due', 'Your child is due for routine vaccinations. Please schedule an appointment.', 'GENERAL', false, '2024-12-17 09:00:00'),
(6, 'Blood Pressure Alert', 'Your recent readings indicate elevated blood pressure. Please follow up with your doctor.', 'GENERAL', false, '2024-12-22 11:30:00'),
(7, 'Medication Refill', 'Your prescription for Donepezil is due for refill. Please contact your pharmacy.', 'PRESCRIPTION', true, '2024-12-20 13:20:00'),
(8, 'Appointment Reminder', 'Your dermatology appointment is in 2 days. Please prepare your medical history.', 'APPOINTMENT', false, '2024-12-25 15:00:00');
