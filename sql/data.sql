call createRole('ROLE_USER', @role_user);
call createRole('ROLE_ADMIN', @role_admin);


call createAccount("drivoadmin@gmail.com", "drivojoy123", "494882000000165001", 1441960422000, "ROLE_ADMIN");
call createAccount("drivouser@gmail.com", "drivojoy123", "494882000000165002", 1441960422000, "ROLE_USER");