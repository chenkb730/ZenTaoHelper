package com.seven.zentao.config

/**
 * Created by Seven on 2018/1/25.
 */

class ZenTaoRegex {
    companion object {

        val TAB_TB = "<tbody.*(?=>)(.|\n)*?</tbody>"

        val TAG_USER = "<i class='icon-user'.*(?=>)(.|\n)?<ul class='dropdown-menu'>"

        val TAG_TR = "<tr.*(?=>)(.|\n)*?</tr>"

        val TAG_BUG_ID = "<input type='checkbox'.*(?=>)(.|\n)*?</td>"

        val ATG_TYPE = "<span class='severity.*(?=>)(.|\n)*?<td>"

        val TAG_BUD_DES = "<td class='text-left nobr'.*(?=>)(.|\n)*?</td>"

    }
}
