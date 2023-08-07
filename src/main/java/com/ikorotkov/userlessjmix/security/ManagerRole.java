package com.ikorotkov.userlessjmix.security;

import com.ikorotkov.userlessjmix.entity.Department;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "manager", code = ManagerRole.CODE)
public interface ManagerRole {
    String CODE = "manager";

    @EntityAttributePolicy(entityClass = Department.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Department.class, actions = EntityPolicyAction.ALL)
    void department();

    @SpecificPolicy(resources = "ui.loginToUi")
    void specific();

    @MenuPolicy(menuIds = "*")
    @ScreenPolicy(screenIds = "*")
    void screens();
}