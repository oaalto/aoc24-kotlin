package day02

object ProblemDampener {
    fun tryToFixReport(report: Report): Report {
        val validReports = report.levels.mapIndexed { index, level ->
            Report(report.levels.filterIndexed { i, _ -> i != index })
        }.filter(Report::isValid)

        return validReports.elementAtOrNull(0) ?: report
    }
}