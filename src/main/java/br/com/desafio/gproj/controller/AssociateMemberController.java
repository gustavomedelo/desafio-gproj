package br.com.desafio.gproj.controller;

import br.com.desafio.gproj.constant.ModelViewConstants;
import br.com.desafio.gproj.dto.form.AssociateForm;
import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.service.AssociateMemberService;
import br.com.desafio.gproj.service.PersonService;
import br.com.desafio.gproj.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class AssociateMemberController {

    private final PersonService personService;
    private final ProjectService projectService;
    private final AssociateMemberService associateMemberService;

    @GetMapping(value = "/associate")
    public String showAssociateMembersPage(Model model) {
        model.addAttribute(ModelViewConstants.MEMBERS_ATTR, personService.findAllEmployee());
        model.addAttribute(ModelViewConstants.PROJECTS_ATTR, projectService.findAllProject());
        model.addAttribute(ModelViewConstants.ASSOCIATE_ATTR, AssociateForm.builder().build());
        return ModelViewConstants.ASSOCIATE_VIEW;
    }

    @PostMapping(value = "/associate")
    public String associateMembersProjects(@ModelAttribute("associate") @Valid AssociateForm associate,
                                           Model model, BindingResult result) {
        if (result.hasErrors()) {
            return ModelViewConstants.ASSOCIATE_VIEW;
        }

        try {
            associateMemberService.associateMemberProject(associate.getIdMember(), associate.getIdProject());
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return showAssociateMembersPage(model);
        }

        return ModelViewConstants.REDIRECT_PROJECTS;
    }
}
