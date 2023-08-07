package com.ikorotkov.userlessjmix.screen.department;

import io.jmix.ui.screen.*;
import com.ikorotkov.userlessjmix.entity.Department;

@UiController("UJ_Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
public class DepartmentBrowse extends StandardLookup<Department> {
}