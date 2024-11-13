package br.com.desafio.gproj.controller;

import br.com.desafio.gproj.constant.ModelViewConstants;
import br.com.desafio.gproj.dto.form.ProjectFormInput;
import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/projects")
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public String showListProject(Model model, HttpServletRequest request) {
        var itemsPerPage = 10;
        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 0;

        var pagedListHolder = new PagedListHolder<>(service.findAllProject());
        pagedListHolder.setPageSize(itemsPerPage);
        pagedListHolder.setPage(currentPage);

        var itemListForPage = pagedListHolder.getPageList();
        int totalPages = pagedListHolder.getPageCount();

        model.addAttribute("itemList", itemListForPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("projects", pagedListHolder);

        return ModelViewConstants.PROJECT_LIST_VIEW;
    }

    @GetMapping(value = "/create")
    public String showCreateProject(Model model) {
        model.addAttribute(ModelViewConstants.PROJECT_FORM, service.buildCreateProjectForm().withReadOnly(false));
        return ModelViewConstants.PROJECT_CREATE_VIEW;
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute(ModelViewConstants.PROJECT_FORM) ProjectFormInput project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return ModelViewConstants.ERROR_VIEW;
        }

        try {
            service.create(project);
        } catch (BusinessException e) {
            model.addAttribute(ModelViewConstants.ERROR_ATTR, e.getMessage());
            return showCreateProject(model);
        }

        return ModelViewConstants.REDIRECT_PROJECTS;
    }

    @GetMapping(value = "/{id}")
    public String showViewProjectPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute(ModelViewConstants.PROJECT_FORM, service.buildProjectFormInputById(id).withReadOnly(true));
        return ModelViewConstants.PROJECT_VIEW;
    }

    @GetMapping(value = "/{id}/update")
    public String showUpdateProject(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute(ModelViewConstants.PROJECT_FORM, service.buildProjectFormInputById(id).withReadOnly(false));
        return ModelViewConstants.PROJECT_UPDATE_VIEW;
    }

    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable("id") Long id,
                                @ModelAttribute(ModelViewConstants.PROJECT_FORM) ProjectFormInput project,
                                Model model,
                                BindingResult result) {
        if (result.hasErrors()) {
            return ModelViewConstants.ERROR_VIEW;
        }

        try {
            service.update(id, project);
        } catch (BusinessException e) {
            model.addAttribute(ModelViewConstants.ERROR_ATTR, e.getMessage());
            return ModelViewConstants.PROJECT_CREATE_VIEW;
        }

        return ModelViewConstants.REDIRECT_PROJECTS;
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {
        try {
            service.delete(id);
        } catch (BusinessException e) {
            model.addAttribute(ModelViewConstants.ERROR_ATTR, e.getMessage());
            return ModelViewConstants.PROJECT_LIST_VIEW;
        }
        return ModelViewConstants.REDIRECT_PROJECTS;
    }
}