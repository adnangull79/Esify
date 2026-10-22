package com.example.esify.data.subject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Spa
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.esify.ui.theme.LeafGreen
import com.example.esify.ui.theme.LightLeafGreen
import com.example.esify.ui.theme.LightMint
import com.example.esify.ui.theme.LightOrange
import com.example.esify.ui.theme.LightPurple
import com.example.esify.ui.theme.OrangeAccent
import com.example.esify.ui.theme.PrimaryGreen
import com.example.esify.ui.theme.PurpleAccent

data class TopicItem(
    val id: String,
    val number: Int,
    val title: String,
    val progress: Int // 0 to 100
)

data class ChapterItem(
    val id: String,
    val number: Int,
    val title: String,
    val description: String,
    val progress: Int, // 0 to 100
    val detailedDescription: String = description,
    val topics: List<TopicItem> = emptyList(),
    val demoCompletedTopics: Int? = null
) {
    val totalTopicsCount: Int get() = topics.size
    val completedTopics: Int get() = demoCompletedTopics ?: topics.count { it.progress == 100 }
    val progressPercent: Int get() = if (topics.isEmpty()) {
        progress.coerceIn(0, 100)
    } else if (demoCompletedTopics != null) {
        (demoCompletedTopics * 100 / topics.size.coerceAtLeast(1)).coerceIn(0, 100)
    } else {
        (topics.count { it.progress == 100 } * 100 / topics.size.coerceAtLeast(1)).coerceIn(0, 100)
    }
}

data class SubjectDetailsItem(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val accentColor: Color,
    val backgroundColor: Color,
    val totalChapters: Int,
    val completedChapters: Int,
    val progress: Float,
    val percentageText: String,
    val chapters: List<ChapterItem>,
    val description: String = "Explore chapters, study notes, MCQs, solved exercises and more."
)

enum class ChapterFilter(val label: String) {
    ALL("All Chapters"),
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed")
}

object SubjectCatalogue {

    private val csChapter1Topics = listOf(
        TopicItem("cs_ch1_t1", 1, "What is a Computer?", 100),
        TopicItem("cs_ch1_t2", 2, "Evolution (History) of Computer", 80),
        TopicItem("cs_ch1_t3", 3, "Generations of Computer", 60),
        TopicItem("cs_ch1_t4", 4, "Types of Computer", 40),
        TopicItem("cs_ch1_t5", 5, "Components of a Computer System", 0),
        TopicItem("cs_ch1_t6", 6, "Input and Output Devices", 0),
        TopicItem("cs_ch1_t7", 7, "Applications of Computer", 0),
        TopicItem("cs_ch1_t8", 8, "Advantages and Disadvantages", 0)
    )

    private val computerScienceChapters = listOf(
        ChapterItem(
            id = "cs_ch1",
            number = 1,
            title = "Introduction to Computer",
            description = "Computer System and Their Types",
            detailedDescription = "Learn the basics of a computer, its evolution, types and applications.",
            progress = 100,
            topics = csChapter1Topics,
            demoCompletedTopics = 6
        ),
        ChapterItem("cs_ch2", 2, "Data Representation", "Number Systems, Binary, etc.", 60),
        ChapterItem("cs_ch3", 3, "Computer System", "Hardware and Software", 25),
        ChapterItem("cs_ch4", 4, "Operating System", "Functions and Types", 0),
        ChapterItem("cs_ch5", 5, "Applications of ICT", "Application Software", 0),
        ChapterItem("cs_ch6", 6, "Problem Solving", "Algorithms and Flowcharts", 0),
        ChapterItem("cs_ch7", 7, "Programming Basics", "Introduction to Programming", 0),
        ChapterItem("cs_ch8", 8, "Networks and Internet", "Basics of Networking", 100),
        ChapterItem("cs_ch9", 9, "Cyber Safety", "Staying Safe Online", 100),
        ChapterItem("cs_ch10", 10, "Database Basics", "Tables and Records", 100),
        ChapterItem("cs_ch11", 11, "Web Basics", "Pages and Browsers", 100),
        ChapterItem("cs_ch12", 12, "Emerging Technologies", "AI, IoT and More", 45)
    )

    private val physicsChapters = listOf(
        ChapterItem("phy_ch1", 1, "Physical Quantities & Measurement", "Base and Derived Quantities", 100),
        ChapterItem("phy_ch2", 2, "Kinematics", "Rest, Motion and Types of Motion", 100),
        ChapterItem("phy_ch3", 3, "Dynamics", "Force, Momentum and Friction", 100),
        ChapterItem("phy_ch4", 4, "Turning Effect of Forces", "Torque and Principles of Moments", 100),
        ChapterItem("phy_ch5", 5, "Gravitation", "Law of Gravitation and Mass of Earth", 100),
        ChapterItem("phy_ch6", 6, "Work and Energy", "Work, Power and Renewable Energy", 100),
        ChapterItem("phy_ch7", 7, "Properties of Matter", "Kinetic Theory, Density and Pressure", 0),
        ChapterItem("phy_ch8", 8, "Thermal Properties of Matter", "Temperature and Specific Heat", 0),
        ChapterItem("phy_ch9", 9, "Transfer of Heat", "Conduction, Convection and Radiation", 0),
        ChapterItem("phy_ch10", 10, "Waves and Sound", "Oscillations and Sound Wave Properties", 0),
        ChapterItem("phy_ch11", 11, "Optics", "Reflection and Refraction of Light", 0),
        ChapterItem("phy_ch12", 12, "Nuclear Physics", "Atomic Nucleus and Radioactivity", 0)
    )

    private val chemistryChapters = listOf(
        ChapterItem("chem_ch1", 1, "Fundamentals of Chemistry", "Branches and Basic Definitions", 100),
        ChapterItem("chem_ch2", 2, "Structure of Atoms", "Rutherford and Bohr Atomic Models", 100),
        ChapterItem("chem_ch3", 3, "Periodic Table & Periodicity", "Modern Periodic Trends and Groups", 100),
        ChapterItem("chem_ch4", 4, "Structure of Molecules", "Ionic, Covalent and Coordinate Bonds", 100),
        ChapterItem("chem_ch5", 5, "Physical States of Matter", "Gases, Liquids and Solid States", 100),
        ChapterItem("chem_ch6", 6, "Solutions", "Saturated, Unsaturated and Solubility", 0),
        ChapterItem("chem_ch7", 7, "Electrochemistry", "Oxidation-Reduction and Cells", 0),
        ChapterItem("chem_ch8", 8, "Chemical Reactivity", "Reactivity of Metals and Non-Metals", 0),
        ChapterItem("chem_ch9", 9, "Environmental Chemistry", "Atmospheric Pollution and Water Quality", 0),
        ChapterItem("chem_ch10", 10, "Acids, Bases and Salts", "pH Concepts and Neutralization", 0),
        ChapterItem("chem_ch11", 11, "Organic Chemistry", "Carbon Compounds and Functional Groups", 0),
        ChapterItem("chem_ch12", 12, "Hydrocarbons", "Alkanes, Alkenes and Alkynes", 0)
    )

    private val biologyChapters = listOf(
        ChapterItem("bio_ch1", 1, "Introduction to Biology", "Divisions and Branches of Biology", 100),
        ChapterItem("bio_ch2", 2, "Solving a Biological Problem", "Biological Method and Hypothesis", 100),
        ChapterItem("bio_ch3", 3, "Biodiversity", "Classification and Five Kingdoms", 100),
        ChapterItem("bio_ch4", 4, "Cells and Tissues", "Microscopy and Cellular Structures", 100),
        ChapterItem("bio_ch5", 5, "Cell Cycle", "Mitosis, Meiosis and Cell Division", 0),
        ChapterItem("bio_ch6", 6, "Enzymes", "Characteristics and Factors Affecting Rates", 0),
        ChapterItem("bio_ch7", 7, "Bioenergetics", "Photosynthesis and Cellular Respiration", 0),
        ChapterItem("bio_ch8", 8, "Nutrition", "Components of Food and Digestion", 0),
        ChapterItem("bio_ch9", 9, "Transport", "Transport in Plants and Blood Circulation", 0),
        ChapterItem("bio_ch10", 10, "Gaseous Exchange", "Respiration in Humans and Plants", 0),
        ChapterItem("bio_ch11", 11, "Homeostasis", "Osmoregulation, Thermoregulation and Excretion", 0),
        ChapterItem("bio_ch12", 12, "Coordination and Control", "Nervous and Chemical Coordination", 0)
    )

    val computerScience = SubjectDetailsItem(
        id = "computer_science",
        name = "Computer Science",
        icon = Icons.Filled.Computer,
        accentColor = PrimaryGreen,
        backgroundColor = LightMint,
        totalChapters = 12,
        completedChapters = 8,
        progress = 8f / 12f,
        percentageText = "67%",
        chapters = computerScienceChapters
    )

    val physics = SubjectDetailsItem(
        id = "physics",
        name = "Physics",
        icon = Icons.Filled.Bolt,
        accentColor = PurpleAccent,
        backgroundColor = LightPurple,
        totalChapters = 12,
        completedChapters = 6,
        progress = 6f / 12f,
        percentageText = "50%",
        chapters = physicsChapters
    )

    val chemistry = SubjectDetailsItem(
        id = "chemistry",
        name = "Chemistry",
        icon = Icons.Filled.Science,
        accentColor = OrangeAccent,
        backgroundColor = LightOrange,
        totalChapters = 12,
        completedChapters = 5,
        progress = 5f / 12f,
        percentageText = "42%",
        chapters = chemistryChapters
    )

    val biology = SubjectDetailsItem(
        id = "biology",
        name = "Biology",
        icon = Icons.Filled.Spa,
        accentColor = LeafGreen,
        backgroundColor = LightLeafGreen,
        totalChapters = 12,
        completedChapters = 4,
        progress = 4f / 12f,
        percentageText = "33%",
        chapters = biologyChapters
    )

    private val subjectsList = listOf(computerScience, physics, chemistry, biology)

    fun getAllSubjects(): List<SubjectDetailsItem> = subjectsList

    fun getSubject(id: String?): SubjectDetailsItem {
        if (id.isNullOrBlank()) return computerScience
        return when (id.lowercase().trim()) {
            "computer_science", "cs" -> computerScience
            "physics" -> physics
            "chemistry" -> chemistry
            "biology" -> biology
            else -> computerScience // Graceful fallback
        }
    }

    fun getChapter(subjectId: String?, chapterId: String?): Pair<SubjectDetailsItem, ChapterItem> {
        val subject = getSubject(subjectId)
        val chapter = if (chapterId.isNullOrBlank()) {
            subject.chapters.firstOrNull() ?: ChapterItem("default", 1, "Chapter 1", "Description", 0)
        } else {
            subject.chapters.find { ch ->
                ch.id.equals(chapterId, ignoreCase = true) ||
                ch.number.toString() == chapterId.filter { it.isDigit() } ||
                ch.id.endsWith(chapterId, ignoreCase = true) ||
                chapterId.endsWith(ch.id, ignoreCase = true)
            } ?: subject.chapters.firstOrNull() ?: ChapterItem("default", 1, "Chapter 1", "Description", 0)
        }
        return subject to chapter
    }
}
