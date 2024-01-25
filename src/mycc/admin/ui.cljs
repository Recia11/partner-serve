(ns mycc.admin.ui
  (:require
    [modulo.api :as mod]
    [reagent.core :as r]
    [bloom.commons.ajax :as ajax]))

(defn admin-page-view []
  (r/with-let
    [data (r/atom nil)
     _ (when (nil? @data)
         (ajax/request {:method :get
                        :uri "/api/admin/all"
                        :on-success (fn [d]
                                      (reset! data d))}))]
    [:div {:dangerouslySetInnerHTML {:__html (:content @data)}}]))

(mod/register-page!
  {:page/id :page.id/admin
   :page/path "/admin"
   :page/nav-label "Admin"
   :page/view #'admin-page-view
   :page/on-enter! (fn [])})
