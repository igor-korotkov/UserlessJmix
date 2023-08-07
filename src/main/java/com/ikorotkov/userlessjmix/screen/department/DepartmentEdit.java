package com.ikorotkov.userlessjmix.screen.department;

import io.jmix.ui.screen.*;
import com.ikorotkov.userlessjmix.entity.Department;

@UiController("UJ_Department.edit")
@UiDescriptor("department-edit.xml")
@EditedEntityContainer("departmentDc")
public class DepartmentEdit extends StandardEditor<Department> {
}