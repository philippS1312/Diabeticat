
#Look for open wildfly instances and close them:
wmic Path win32_process Where "CommandLine Like '%dvd-rental-customer-bootable.jar%'" Call Terminate
wmic Path win32_process Where "CommandLine Like '%dvd-rental-film-bootable.jar%'" Call Terminate
wmic Path win32_process Where "CommandLine Like '%dvd-rental-store-bootable.jar%'" Call Terminate
wmic Path win32_process Where "CommandLine Like '%dvd-rental-ui-bootable.jar%'" Call Terminate