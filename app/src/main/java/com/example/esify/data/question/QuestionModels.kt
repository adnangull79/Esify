package com.example.esify.data.question

import com.example.esify.data.subject.ChapterItem
import com.example.esify.data.subject.SubjectCatalogue
import com.example.esify.data.subject.SubjectDetailsItem
import com.example.esify.data.subject.TopicItem

data class QuestionOption(
    val id: String, // e.g. "A", "B", "C", "D"
    val text: String
)

data class McqQuestion(
    val id: String,
    val topicId: String,
    val question: String,
    val options: List<QuestionOption>,
    val correctOptionId: String,
    val explanation: String
) {
    init {
        require(options.size == 4) { "Every MCQ must contain exactly 4 options" }
        require(options.any { it.id == correctOptionId }) { "Correct option ID must match one of the options" }
    }

    val correctOption: QuestionOption
        get() = options.first { it.id == correctOptionId }
}

data class ShortQuestion(
    val id: String,
    val topicId: String,
    val questionNumber: Int = 1,
    val question: String,
    val modelAnswer: String,
    val answerPoints: List<String> = emptyList(),
    val explanation: String = ""
)

enum class PracticeMode(val title: String, val subtitle: String) {
    MCQ("MCQs", "All Questions"),
    SHORT_QUESTIONS("Short Questions", "Chapter Qs"),
    QUIZ("Take Quiz", "Practice Now");

    companion object {
        fun fromRoute(mode: String?): PracticeMode {
            return when (mode?.lowercase()?.trim()) {
                "mcq", "mcqs" -> MCQ
                "short_questions", "short", "questions" -> SHORT_QUESTIONS
                "quiz", "take_quiz" -> QUIZ
                else -> MCQ
            }
        }
    }
}

object QuestionCatalogue {

    private val csTopic1Mcqs = listOf(
        McqQuestion(
            id = "mcq_1",
            topicId = "cs_ch1_t1",
            question = "What is an electronic machine that accepts data, processes it according to instructions, and produces information called?",
            options = listOf(
                QuestionOption("A", "Calculator"),
                QuestionOption("B", "Computer"),
                QuestionOption("C", "Printer"),
                QuestionOption("D", "Scanner")
            ),
            correctOptionId = "B",
            explanation = "A computer is an electronic device that accepts raw data as input, processes it according to stored instructions, and produces meaningful information as output."
        ),
        McqQuestion(
            id = "mcq_2",
            topicId = "cs_ch1_t1",
            question = "What is raw, unorganized facts and figures that are entered into a computer called?",
            options = listOf(
                QuestionOption("A", "Information"),
                QuestionOption("B", "Data"),
                QuestionOption("C", "Program"),
                QuestionOption("D", "Output")
            ),
            correctOptionId = "B",
            explanation = "Data refers to raw, unprocessed facts and figures. Once processed and organized into meaningful context, it becomes information."
        ),
        McqQuestion(
            id = "mcq_3",
            topicId = "cs_ch1_t1",
            question = "Which operation involves displaying or printing the processed results of a computer system?",
            options = listOf(
                QuestionOption("A", "Input"),
                QuestionOption("B", "Processing"),
                QuestionOption("C", "Output"),
                QuestionOption("D", "Storage")
            ),
            correctOptionId = "C",
            explanation = "Output is the stage where processed data (information) is presented to the user through devices such as monitors and printers."
        ),
        McqQuestion(
            id = "mcq_4",
            topicId = "cs_ch1_t1",
            question = "Which component is known as the brain of the computer that performs calculations and logical operations?",
            options = listOf(
                QuestionOption("A", "Hard Disk"),
                QuestionOption("B", "Central Processing Unit (CPU)"),
                QuestionOption("C", "Random Access Memory (RAM)"),
                QuestionOption("D", "Motherboard")
            ),
            correctOptionId = "B",
            explanation = "The Central Processing Unit (CPU) executes program instructions, performs arithmetic and logic calculations, and coordinates computer operations."
        ),
        McqQuestion(
            id = "mcq_5",
            topicId = "cs_ch1_t1",
            question = "Which of the following is primarily an input device used to enter text and commands?",
            options = listOf(
                QuestionOption("A", "Monitor"),
                QuestionOption("B", "Keyboard"),
                QuestionOption("C", "Speaker"),
                QuestionOption("D", "Plotter")
            ),
            correctOptionId = "B",
            explanation = "A keyboard is a primary input device that allows users to input text, numbers, and system commands into the computer."
        ),
        McqQuestion(
            id = "mcq_6",
            topicId = "cs_ch1_t1",
            question = "Which device produces a soft-copy visual display of processed information?",
            options = listOf(
                QuestionOption("A", "Monitor"),
                QuestionOption("B", "Mouse"),
                QuestionOption("C", "Microphone"),
                QuestionOption("D", "Barcode Reader")
            ),
            correctOptionId = "A",
            explanation = "A monitor is an output device that visualizes information on a screen in electronic soft-copy form."
        ),
        McqQuestion(
            id = "mcq_7",
            topicId = "cs_ch1_t1",
            question = "The physical, tangible parts of a computer system that you can touch and see are termed as:",
            options = listOf(
                QuestionOption("A", "Software"),
                QuestionOption("B", "Hardware"),
                QuestionOption("C", "Firmware"),
                QuestionOption("D", "Malware")
            ),
            correctOptionId = "B",
            explanation = "Hardware refers to the physical components of a computer system such as the monitor, keyboard, CPU, and circuit boards."
        ),
        McqQuestion(
            id = "mcq_8",
            topicId = "cs_ch1_t1",
            question = "Which of the following represents the correct sequence of basic computer operations?",
            options = listOf(
                QuestionOption("A", "Process → Input → Output → Store"),
                QuestionOption("B", "Output → Input → Store → Process"),
                QuestionOption("C", "Input → Process → Output → Storage"),
                QuestionOption("D", "Store → Process → Input → Output")
            ),
            correctOptionId = "C",
            explanation = "The standard information processing cycle follows the sequential order of Input (receiving data), Processing (computing data), Output (displaying results), and Storage (saving data)."
        )
    )

    private val csTopic1ShortQuestions = listOf(
        ShortQuestion(
            id = "sq_cs_1",
            topicId = "cs_ch1_t1",
            questionNumber = 1,
            question = "What is an electronic computer? Define its basic functions.",
            modelAnswer = "A computer is an electronic machine that accepts raw data as input, processes it according to stored instructions, produces meaningful information as output, and stores the results for future reference.",
            answerPoints = listOf(
                "Input: Accepts raw data and instructions entered through input devices (e.g., keyboard, mouse).",
                "Processing: Performs calculations and logical operations on data using the Central Processing Unit (CPU).",
                "Output: Presents processed results (information) to users on display screens or printouts.",
                "Storage: Retains data, programs, and output permanently in secondary storage devices for later retrieval."
            ),
            explanation = "Computers reliably execute the complete Information Processing Cycle (IPOS)."
        ),
        ShortQuestion(
            id = "sq_cs_2",
            topicId = "cs_ch1_t1",
            questionNumber = 2,
            question = "Write any four key characteristics of a modern digital computer.",
            modelAnswer = "Modern digital computers possess several distinguishing characteristics that enable them to outperform human manual calculation in diverse fields.",
            answerPoints = listOf(
                "Speed: Capable of executing millions or billions of instructions per second (MIPS/GFLOPS).",
                "Accuracy: Consistently performs calculations without errors unless incorrect input or instructions are provided (GIGO principle).",
                "Diligence: Can perform repetitive tasks for hours continuously without tiredness, lack of concentration, or emotional fatigue.",
                "Versatility: Capable of executing completely different applications ranging from scientific simulations to graphic design.",
                "Storage Capacity: Can store and instantly recall huge volumes of data across internal memory and external secondary storage."
            ),
            explanation = "These fundamental characteristics make computers indispensable across industry, education, and scientific research."
        ),
        ShortQuestion(
            id = "sq_cs_3",
            topicId = "cs_ch1_t1",
            questionNumber = 3,
            question = "Differentiate between data and information with examples.",
            modelAnswer = "Data and information represent two stages of the computer processing cycle. Data is the raw unprocessed input, while information is the structured output that conveys meaning.",
            answerPoints = listOf(
                "Data: Unorganized, raw facts, numbers, and symbols without context (e.g., individual exam marks like 85, 92, 78).",
                "Information: Processed, organized, and structured data that assists in decision-making (e.g., a student report card showing overall percentage and grade)."
            ),
            explanation = "Data serves as the input, while information is the refined, meaningful output."
        ),
        ShortQuestion(
            id = "sq_cs_4",
            topicId = "cs_ch1_t1",
            questionNumber = 4,
            question = "What is an input device? Give any four common examples.",
            modelAnswer = "An input device is an electro-mechanical hardware peripheral used to enter data, commands, and user signals into a computer system for processing.",
            answerPoints = listOf(
                "Keyboard: Primary text entry device with alphanumeric and function keys.",
                "Mouse: Handheld pointing device used to point, click, and interact with graphical user interfaces.",
                "Scanner: Optical device that converts physical documents and photographs into digital images.",
                "Microphone: Audio capture device that converts sound waves into digital audio signals."
            ),
            explanation = "Input devices translate human-understandable information into binary signals for the CPU."
        ),
        ShortQuestion(
            id = "sq_5",
            topicId = "cs_ch1_t1",
            questionNumber = 5,
            question = "Explain the basic functions involved in the Information Processing Cycle.",
            modelAnswer = "The Information Processing Cycle consists of four continuous sequential operations through which a computer converts raw data into actionable knowledge.",
            answerPoints = listOf(
                "Input: Capturing user data and instructions from input peripherals.",
                "Processing: Manipulating, sorting, and computing data in the CPU's Arithmetic Logic Unit.",
                "Output: Presenting intelligible results to the user via monitors, speakers, or printers.",
                "Storage: Saving processed data permanently on storage media (Hard drives, SSDs) for future reuse."
            ),
            explanation = "The cycle operates seamlessly under control of operating system instructions."
        )
    )

    private val physicsTopic1ShortQuestions = listOf(
        ShortQuestion(
            id = "sq_phy_1",
            topicId = "phy_ch1_t1",
            questionNumber = 1,
            question = "Define physics and briefly describe any four of its main branches.",
            modelAnswer = "Physics is the branch of science that deals with the study of matter, energy, and the fundamental mutual relationship between them.",
            answerPoints = listOf(
                "Mechanics: The study of the motion of objects, its causes, and effects under the action of forces.",
                "Heat / Thermodynamics: Deals with the nature of heat, modes of heat transfer, and conversion of heat into mechanical work.",
                "Electromagnetism: The study of electric charges at rest and in motion, their effects, and their relationship with magnetic fields.",
                "Atomic & Nuclear Physics: Deals with the structure, properties, and reactions of atoms and atomic nuclei."
            ),
            explanation = "Physics forms the foundation for engineering, astronomy, and technological sciences."
        ),
        ShortQuestion(
            id = "sq_phy_2",
            topicId = "phy_ch1_t1",
            questionNumber = 2,
            question = "What are physical quantities? Differentiate between base and derived quantities with examples.",
            modelAnswer = "All measurable quantities are called physical quantities (e.g., length, mass, time, temperature). They are divided into two principal categories.",
            answerPoints = listOf(
                "Base Quantities: The minimum number of physical quantities in terms of which other quantities can be defined and expressed (e.g., Length, Mass, Time, Temperature, Electric Current, Amount of Substance, Luminous Intensity).",
                "Derived Quantities: The quantities that are expressed in terms of base quantities through mathematical multiplication or division (e.g., Speed = distance/time, Force = mass × acceleration, Volume, Pressure, Work)."
            ),
            explanation = "The International System recognizes exactly 7 fundamental base physical quantities."
        ),
        ShortQuestion(
            id = "sq_phy_3",
            topicId = "phy_ch1_t1",
            questionNumber = 3,
            question = "What is the International System of Units (SI)? State any four base units with their symbols.",
            modelAnswer = "The International System of Units (SI) is a worldwide standard metric measurement system adopted in 1960 by the 11th General Conference on Weights and Measures to establish universal measurement consistency.",
            answerPoints = listOf(
                "Length: Measured in metre (symbol: m).",
                "Mass: Measured in kilogram (symbol: kg).",
                "Time: Measured in second (symbol: s).",
                "Electric Current: Measured in ampere (symbol: A)."
            ),
            explanation = "Standard units prevent discrepancies in international scientific research and commerce."
        ),
        ShortQuestion(
            id = "sq_phy_4",
            topicId = "phy_ch1_t1",
            questionNumber = 4,
            question = "What is a Vernier Calliper? Define its least count and write its formula.",
            modelAnswer = "A Vernier Calliper is a precision laboratory instrument used to measure small dimensions (lengths, internal/external diameters, and depths) with higher accuracy than a standard metre rule.",
            answerPoints = listOf(
                "Least Count Definition: The smallest value that can be accurately measured by a measuring instrument.",
                "Formula: Least Count (LC) = Smallest division on main scale / Total number of divisions on Vernier scale.",
                "Standard Value: For a metric calliper with a 1 mm main division and 10 Vernier divisions, LC = 1 mm / 10 = 0.1 mm (0.01 cm)."
            ),
            explanation = "The least count is also known as the Vernier constant of the instrument."
        ),
        ShortQuestion(
            id = "sq_phy_5",
            topicId = "phy_ch1_t1",
            questionNumber = 5,
            question = "Explain what is meant by zero error in a measuring instrument and state its types.",
            modelAnswer = "Zero error is a systematic mechanical error that occurs when the zero mark of the measuring scale does not coincide with the zero mark of the reference scale when measuring jaws are brought into contact.",
            answerPoints = listOf(
                "Zero Error Absent: Both zero marks align exactly; no correction is required.",
                "Positive Zero Error: The Vernier scale zero lies to the right of the main scale zero; the error value is subtracted from the observed reading.",
                "Negative Zero Error: The Vernier scale zero lies to the left of the main scale zero; the error value is added to the observed reading."
            ),
            explanation = "Correcting for zero error ensures true, scientifically accurate experimental readings."
        )
    )

    private val chemistryTopic1ShortQuestions = listOf(
        ShortQuestion(
            id = "sq_chem_1",
            topicId = "chem_ch1_t1",
            questionNumber = 1,
            question = "Define chemistry and briefly describe any four of its major branches.",
            modelAnswer = "Chemistry is the branch of science that studies the composition, structure, properties, and chemical reactions of matter, along with the fundamental laws governing these changes.",
            answerPoints = listOf(
                "Physical Chemistry: Studies the relationship between the physical properties of substances and their chemical composition and reactions.",
                "Organic Chemistry: Studies covalent compounds containing carbon and hydrogen (hydrocarbons) and their diverse derivatives.",
                "Inorganic Chemistry: Studies all chemical elements and their compounds, except hydrocarbons and their derivatives.",
                "Biochemistry: Studies the chemical substances, enzymatic reactions, and metabolic processes occurring inside living organisms."
            ),
            explanation = "Chemistry connects physical concepts to biological systems, earning the title 'The Central Science'."
        ),
        ShortQuestion(
            id = "sq_chem_2",
            topicId = "chem_ch1_t1",
            questionNumber = 2,
            question = "Differentiate between an element, a compound, and a mixture with examples.",
            modelAnswer = "Matter can be classified based on purity and chemical bonding into elements, compounds, and mixtures.",
            answerPoints = listOf(
                "Element: A pure substance made of only one type of atom with identical atomic numbers, which cannot be broken down by ordinary chemical means (e.g., Oxygen O₂, Gold Au, Iron Fe).",
                "Compound: A substance formed when two or more elements chemically combine together in a fixed ratio by mass (e.g., Pure Water H₂O in 1:8 mass ratio, Table Salt NaCl).",
                "Mixture: An impure substance formed by physically mixing two or more elements or compounds in any proportion without chemical bonding (e.g., Air, Brass, Sugar solution)."
            ),
            explanation = "Compounds possess unique chemical properties distinct from their constituent elements, while mixtures retain individual properties."
        ),
        ShortQuestion(
            id = "sq_chem_3",
            topicId = "chem_ch1_t1",
            questionNumber = 3,
            question = "What is the difference between atomic number and mass number? Explain with an example.",
            modelAnswer = "Atomic number and mass number are fundamental integer values that define the nuclear composition of an atom.",
            answerPoints = listOf(
                "Atomic Number (Z): The total number of protons present in the nucleus of an atom (e.g., Carbon has Z = 6 protons).",
                "Mass Number (A): The total number of protons and neutrons (nucleons) present in the nucleus of an atom (A = Z + N).",
                "Example: A standard Carbon-12 atom has an atomic number Z = 6 and a mass number A = 12 (6 protons + 6 neutrons)."
            ),
            explanation = "The atomic number uniquely defines the identity and position of an element in the periodic table."
        ),
        ShortQuestion(
            id = "sq_chem_4",
            topicId = "chem_ch1_t1",
            questionNumber = 4,
            question = "Define relative atomic mass and atomic mass unit (amu).",
            modelAnswer = "Because individual atoms have unimaginably tiny masses (~10⁻²⁴ g), their masses are expressed relative to a stable carbon-12 isotope reference standard.",
            answerPoints = listOf(
                "Relative Atomic Mass (Ar): The average mass of an atom of an element compared to exactly one-twelfth (1/12th) the mass of one Carbon-12 atom.",
                "Atomic Mass Unit (amu): The standard unit of mass defined as exactly 1/12th the mass of one single atom of Carbon-12.",
                "Numerical Equivalence: 1 amu = 1.66 × 10⁻²⁴ grams (or 1.66 × 10⁻²⁷ kg)."
            ),
            explanation = "Carbon-12 was adopted internationally as the unified atomic mass standard in 1961."
        ),
        ShortQuestion(
            id = "sq_chem_5",
            topicId = "chem_ch1_t1",
            questionNumber = 5,
            question = "Differentiate between an empirical formula and a molecular formula with examples.",
            modelAnswer = "Chemical formulas denote the elemental composition and atomic proportions present in chemical substances.",
            answerPoints = listOf(
                "Empirical Formula: The simplest whole-number ratio of atoms of each element present in a compound (e.g., the empirical formula of Glucose is CH₂O).",
                "Molecular Formula: The actual number of atoms of each element present in one molecule of that compound (e.g., the molecular formula of Glucose is C₆H₁₂O₆).",
                "Mathematical Relation: Molecular Formula = (Empirical Formula)ₙ, where n is a whole number integer."
            ),
            explanation = "For simple compounds like water (H₂O) or carbon dioxide (CO₂), the empirical and molecular formulas are identical."
        )
    )

    private val biologyTopic1ShortQuestions = listOf(
        ShortQuestion(
            id = "sq_bio_1",
            topicId = "bio_ch1_t1",
            questionNumber = 1,
            question = "Define biology and explain its three major divisions.",
            modelAnswer = "Biology is the scientific study of life and living organisms (derived from Greek words 'bios' meaning life and 'logos' meaning study or discourse).",
            answerPoints = listOf(
                "Zoology: The division of biology that deals with the scientific study of animals, their anatomy, physiology, and habitats.",
                "Botany: The division of biology that deals with the study of plants, including their structure, growth, metabolism, and reproduction.",
                "Microbiology: The division of biology that deals with microscopic organisms such as bacteria, viruses, microscopic algae, and protozoa."
            ),
            explanation = "These three core divisions serve as the foundation for specialized biological disciplines."
        ),
        ShortQuestion(
            id = "sq_bio_2",
            topicId = "bio_ch1_t1",
            questionNumber = 2,
            question = "Briefly describe any four specialized branches of biology.",
            modelAnswer = "To study specific morphological and functional aspects of organisms in detail, biology is divided into specialized branches.",
            answerPoints = listOf(
                "Morphology: Study of the external form, shape, and structure of living organisms.",
                "Anatomy: Study of the internal structures and bodily organs of living organisms revealed through dissection.",
                "Histology: The microscopic examination and study of plant and animal tissues.",
                "Physiology: Study of the functions and biochemical mechanisms of different parts of living organisms."
            ),
            explanation = "Integrated biological branches allow scientists to study life from molecular to organismal levels."
        ),
        ShortQuestion(
            id = "sq_bio_3",
            topicId = "bio_ch1_t1",
            questionNumber = 3,
            question = "What is the relationship of biology with other sciences? Give two examples.",
            modelAnswer = "Interdisciplinary sciences integrate biological principles with other fundamental sciences to investigate complex living phenomena.",
            answerPoints = listOf(
                "Biophysics: Applies the laws and principles of physics to biological processes (e.g., lever mechanics in animal limbs, fluid pressure in blood vessels).",
                "Biochemistry: Studies the chemical reactions and molecular substances occurring inside living organisms (e.g., photosynthesis, cellular respiration, digestion).",
                "Biomathematics: Uses mathematical models, formulas, and statistical techniques to analyze biological data and experimental results."
            ),
            explanation = "Interdisciplinary research accelerates modern medical, pharmaceutical, and biotechnological advances."
        ),
        ShortQuestion(
            id = "sq_bio_4",
            topicId = "bio_ch1_t1",
            questionNumber = 4,
            question = "Name the levels of biological organization from simplest to most complex.",
            modelAnswer = "Living matter exhibits distinct hierarchical levels of structural and functional organization that increase in complexity.",
            answerPoints = listOf(
                "1. Subatomic and Atomic Level: Fundamental particles forming bioelements (C, H, O, N, P, Ca).",
                "2. Molecular Level: Atoms combining to form biomolecules (glucose, amino acids, DNA).",
                "3. Organelle & Cellular Level: Biomolecules assembling into organelles (mitochondria, nucleus) and basic units of life (cells).",
                "4. Tissue Level: Groups of specialized cells performing a common function (e.g., muscle or vascular tissue).",
                "5. Organ & Organ System Level: Different tissues working together in organs (stomach) and systems (digestive system).",
                "6. Organism Level: Coordinated systems functioning together to form an individual living organism."
            ),
            explanation = "Beyond the individual organism, organization extends into Populations, Communities, Ecosystems, and the Biosphere."
        ),
        ShortQuestion(
            id = "sq_bio_5",
            topicId = "bio_ch1_t1",
            questionNumber = 5,
            question = "State the contributions of Jabir ibn Hayyan and Abdul Malik Asma'i to early biology.",
            modelAnswer = "Muslim scholars laid crucial empirical and observational foundations in biological and natural sciences during the Golden Age.",
            answerPoints = listOf(
                "Jabir ibn Hayyan (721–815 AD): Renowned chemist and natural philosopher who authored seminal early texts on plants and animals ('Kitab al-Nabatat' and 'Kitab al-Hayawan').",
                "Abdul Malik Asma'i (740–828 AD): Regarded as the first Muslim zoologist who conducted detailed morphological and anatomical studies on animals; author of 'Kitab al-Kheil' (horses), 'Kitab al-Ibil' (camels), and 'Kitab Khalq al-Insan' (human anatomy)."
            ),
            explanation = "Their scientific observations contributed significantly to both Eastern and European natural history."
        )
    )

    fun getMcqs(topicId: String?): List<McqQuestion> {
        if (topicId.isNullOrBlank()) return csTopic1Mcqs
        return when (topicId.lowercase().trim()) {
            "cs_ch1_t1", "t1", "1" -> csTopic1Mcqs
            else -> csTopic1Mcqs // Graceful fallback
        }
    }

    fun getShortQuestions(topicId: String?, subjectId: String? = null): List<ShortQuestion> {
        val resolvedKey = topicId?.lowercase()?.trim()
        val subjectKey = subjectId?.lowercase()?.trim()

        return when {
            resolvedKey in listOf("cs_ch1_t1", "cs_t1") -> csTopic1ShortQuestions
            resolvedKey in listOf("phy_ch1_t1", "phy_t1") -> physicsTopic1ShortQuestions
            resolvedKey in listOf("chem_ch1_t1", "chem_t1") -> chemistryTopic1ShortQuestions
            resolvedKey in listOf("bio_ch1_t1", "bio_t1") -> biologyTopic1ShortQuestions
            subjectKey == "physics" && (resolvedKey == null || resolvedKey.contains("phy") || resolvedKey in listOf("1", "t1")) -> physicsTopic1ShortQuestions
            subjectKey == "chemistry" && (resolvedKey == null || resolvedKey.contains("chem") || resolvedKey in listOf("1", "t1")) -> chemistryTopic1ShortQuestions
            subjectKey == "biology" && (resolvedKey == null || resolvedKey.contains("bio") || resolvedKey in listOf("1", "t1")) -> biologyTopic1ShortQuestions
            resolvedKey in listOf("1", "t1") -> csTopic1ShortQuestions
            topicId.isNullOrBlank() && subjectKey == null -> csTopic1ShortQuestions
            else -> emptyList() // Returns empty list for unpopulated topics to prevent stale data
        }
    }

    fun resolveTopic(
        subjectId: String?,
        chapterId: String?,
        topicId: String?
    ): Triple<SubjectDetailsItem, ChapterItem, TopicItem> {
        val (subject, chapter) = SubjectCatalogue.getChapter(subjectId, chapterId)
        val defaultTopic = when (subject.id.lowercase().trim()) {
            "physics" -> TopicItem("phy_ch1_t1", 1, "Physical Quantities & Measurement", 100)
            "chemistry" -> TopicItem("chem_ch1_t1", 1, "Fundamentals of Chemistry", 100)
            "biology" -> TopicItem("bio_ch1_t1", 1, "Introduction to Biology", 100)
            else -> TopicItem("cs_ch1_t1", 1, "What is a Computer?", 100)
        }
        val topic = if (topicId.isNullOrBlank()) {
            chapter.topics.firstOrNull() ?: defaultTopic
        } else {
            chapter.topics.find {
                it.id.equals(topicId, ignoreCase = true) ||
                it.number.toString() == topicId.filter { char -> char.isDigit() } ||
                it.id.endsWith(topicId, ignoreCase = true)
            } ?: if (topicId.equals(defaultTopic.id, ignoreCase = true) || topicId.contains(subject.id.take(3), ignoreCase = true)) {
                defaultTopic
            } else {
                chapter.topics.firstOrNull() ?: defaultTopic
            }
        }
        return Triple(subject, chapter, topic)
    }
}
